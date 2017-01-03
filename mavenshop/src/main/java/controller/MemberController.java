package controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dao.ItemDao;
import dao.MemberDao;
import dao.SaleDao;
import dao.SaleLineDao;
import exception.AdminRequiredException;
import exception.ErroPassException;
import exception.LoginRequiredException;
import exception.MismatchIdException;
import logic.Cart;
import logic.Item;
import logic.Member;
import logic.Sale;
import logic.SaleLine;
import logic.Shop;


@Controller
public class MemberController {
	@Autowired
	Shop shopService;
	@Autowired
	MemberDao dao;
    @Autowired
    SaleDao saledao;
    @Autowired
    SaleLineDao saleLinedao;
    @Autowired
    ItemDao itemDao;
	
	private void logincheck(HttpServletRequest request){
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		if(id==null){
			id ="";
		}
		Member member = (Member)session.getAttribute("USER_KEY");
		if(member ==null){
			throw new LoginRequiredException("�α��� �ʼ�");
	   }
		if(!member.getId().equals(id) && !member.getId().equals("admin")){
			throw new MismatchIdException();
			
		}
	}
	private void admincheck(HttpServletRequest request){
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("USER_KEY");
		logincheck(request);
		if(!member.getId().equals("admin")){
			throw new AdminRequiredException();
		}
	}
	@RequestMapping("member/loginForm")
	public String toLoginView() {
		return "member/loginForm";
	}

	@ModelAttribute
	public Member getMember() {
		return new Member();
	}

	@RequestMapping("member/login")
	public ModelAndView onSubmit(@Valid Member member, BindingResult bindingResult, HttpSession session) {
		ModelAndView mav = new ModelAndView("member/loginForm");
		if (bindingResult.hasErrors()) {
			bindingResult.reject("error.login.member");
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		
			Member logininfo = dao.getmemberinfo(member.getId(), member.getPass());
			if(logininfo == null){
				bindingResult.reject("error.login.member");
				mav.getModel().putAll(bindingResult.getModel());
				return mav;
			}
			session.setAttribute("USER_KEY", logininfo);
			if (session.getAttribute("CART_KEY") == null) {
				session.setAttribute("CART_KEY", shopService.getCart());
			}
			mav.setViewName("redirect:/item/list.html");
			mav.addObject("logininfo", logininfo);
			return mav;
		
	}

	@RequestMapping("member/registForm")
	public ModelAndView userEntry() {
		ModelAndView mav = new ModelAndView("member/userEntry");

		return mav;
	}

	// @Valid �ɹ����� ��ü�� �ѱ������ ��ȿ�� �˻縦 �ض� ~
//	@RequestMapping("member/userEntry")
//	public ModelAndView userSubmit(@Valid User user, BindingResult bindingResult, HttpSession session)
//			throws Exception {
//		ModelAndView mav = new ModelAndView();
//		if (bindingResult.hasErrors()) {
//			mav.getModel().putAll(bindingResult.getModel());
//			return mav;
//		}
//		try {
//			shopService.createUser(user);
//			mav.setViewName("member/login");
//			mav.addObject("user", user);
//		} catch (DataIntegrityViolationException e) {
//			// �̹� ������ ���̵� ���� �Ҷ� ������ ����~
//			bindingResult.reject("error.duplicate.user");
//			mav.getModel().putAll(bindingResult.getModel());
//		}
//		return mav;
//	}

	// ����ȯ (String ->Date Ÿ������ �ν� + ��ȿ���˻� )
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		// birthDay =>typeMismatch.birthDay ��� �����ڵ� ���
		binder.registerCustomEditor(Date.class, "birthDay", new CustomDateEditor(dateFormat, false));

	}

	@RequestMapping("member/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mav = new ModelAndView("member/login");
		mav.setViewName("redirect:/item/list.html");
		return mav;
	}

	@RequestMapping("member/joinForm")
	public ModelAndView joinForm() {
		ModelAndView mav = new ModelAndView("member/joinForm");
		Member member = new Member();
		mav.addObject("member", member);
		return mav;
	}

	@RequestMapping("member/joinProcess")
	public ModelAndView joinProcess(@Valid Member member,BindingResult bindingResult){
		ModelAndView mav = new ModelAndView("member/joinForm");
		if(bindingResult.hasErrors()){
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		int result = dao.memberInsert(member);
		//���Ϻ����� 
		sendMail(member);
		if(result > 0){
			mav.setViewName("redirect:/member/loginForm.html");
		}else{
			mav.setViewName("redirect:/member/loginForm.html");
		}
		return mav;
	}
	private void sendMail(Member member) {
		//���� ��ü ����
	MyAuthenticator auth = new MyAuthenticator("test@mesedu.co.kr","goodee@0205");
	//("������ ������� ","���� �ּ� ��й�ȣ")
		Properties prop = new Properties();
		//���� ���� ���� mail.pop3.host , 
		prop.put("mail.pop3.host","www.mesedu.co.kr");
		//�۽� ���� ����
		prop.put("mail.smtp.host","www.mesedu.co.kr");
		prop.put("mail.user","test");
		//�����»��
		prop.put("mail.from","test@mesedu.co.kr");
		prop.put("mail.debug","false");
		//�������θ� Ȯ�� -������ Ʈ�� !!!
		prop.put("mail.smtp.auth","true");
		prop.put("mail.smtp.port",587);//cafe24 , ���� ��Ʈ ��ȣ
		
		Session session = Session.getInstance(prop,auth);
		
		MimeMessage msg =new MimeMessage(session);
		
		try{
			
			msg.setFrom(new InternetAddress("test@mesedu.co.kr"));
			InternetAddress[] addrs = {new InternetAddress(member.getEmail())};
			msg.setRecipients(Message.RecipientType.TO, addrs);
			msg.setSentDate(new Date());
			//msg.setText(member.getId()+"ȸ������ Spring ���θ� ������ ȯ���մϴ� .!!");
			InternetAddress from = new InternetAddress("test@mesedu.co.kr");
			InternetAddress to = new InternetAddress(member.getEmail());
			msg.setFrom(from);
			msg.addRecipient(Message.RecipientType.TO, to);//�Ѹ��� ������� ���
			//msg.setRecipients(Message.RecipientType.TO, addrs);//�������� ....
			msg.setSubject("spring ���θ� ȸ�� ���Ծȳ� ");
			//MimeBodyPart body = new MimeBodyPart();
			msg.setContent(member.getId()+"ȸ������ Spring ���θ� ������ ȯ���մϴ� .!!","text/html;charset=euc-kr");
			Transport.send(msg);
		}catch(MessagingException me){
			me.printStackTrace();
		}
	}
	//���� Ŭ����  
	public final class MyAuthenticator extends Authenticator{
		private String id;
		private String pw;
		public MyAuthenticator(String id , String pw) {
			this.id = id;
			this.pw =pw;
		}
		@Override
		protected PasswordAuthentication
		                   getPasswordAuthentication(){
			return new PasswordAuthentication(id,pw);
		}
	}
	@RequestMapping("member/memberimg")
	public String imgForm(){
		return "memberimg";
	}
	@RequestMapping("member/memberimg2")
	public ModelAndView imgProcess(MultipartFile picture, HttpServletRequest request){
		String picturePath = request.getServletContext().getRealPath("/")+"/picture/";
		FileOutputStream fos =null;
		InputStream in =null;
		try{
			fos = new FileOutputStream(picturePath + picture.getOriginalFilename());
			in =picture.getInputStream();
			int data;
			while ((data = in.read())!=-1) {
				fos.write(data);
			}
			fos.close();
			in.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		//thumnail �̹��� 
		ParameterBlock pb = new ParameterBlock();
		pb.add(picturePath + picture.getOriginalFilename());
		RenderedOp rop =JAI.create("fileload", pb);
		BufferedImage bigImg = rop.getAsBufferedImage();
		int width = bigImg.getWidth()/5;
		int height = bigImg.getHeight()/5;
		BufferedImage thumb = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = thumb.createGraphics();
		g.drawImage(bigImg, 0, 0, width, height, null);
		File f = new File(picturePath + "sm_"+picture.getOriginalFilename());
		try{
			ImageIO.write(thumb, "jpg", f);
		}catch(IOException e){
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("memberimg2");
		mav.addObject("picture",picture.getOriginalFilename());
		return mav;
	}
	@RequestMapping("member/admin")
	public ModelAndView admin(HttpServletRequest request){
		admincheck(request);
		ModelAndView mav = new ModelAndView("member/admin");
		List<Member> memberlist = new ArrayList<>();
		memberlist = dao.memberList();
		mav.addObject("memberlist",memberlist);
		return mav;
	}
	@RequestMapping("member/info")
	public ModelAndView info(String id, HttpServletRequest request){
		logincheck(request);		
		ModelAndView mav = new ModelAndView();
		Member member =(Member)dao.selectone(id);
		mav.addObject("member",member);
		
		return mav;
	}
	@RequestMapping("member/updateForm")
	public ModelAndView updateFrom(String id , HttpServletRequest request){
		logincheck(request);
		ModelAndView mav = new ModelAndView("member/updateForm");
		Member member = dao.selectone(id);
		mav.addObject("member",member);
		return mav;
		
	}
	@RequestMapping("member/update")
	public ModelAndView update(@Valid Member member , BindingResult bindingResult, HttpServletRequest request){
		logincheck(request);
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("USER_KEY");
		ModelAndView mav = new ModelAndView("member/updateForm");
		if(bindingResult.hasErrors()){
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		dao.updatemember(member);
		if(m.getId().equals("admin")){
			mav.setViewName("redirect:/member/admin.html");
			return mav;
		}else{
			mav.addObject("redirect:/member/info.html?id="+m.getId());
		}
		
		return mav;
	}
	@RequestMapping("member/deleteForm")
	public ModelAndView deleteForm(String id, HttpServletRequest request){
		return info(id,request);
	}
	
	@RequestMapping("member/delete")
	public ModelAndView delete(String pass,HttpServletRequest request ,String id){
		logincheck(request);
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("USER_KEY");
		Member mdb = (Member)dao.selectone(id);
		if(mdb.getId().equals(m.getId())){
			if(mdb.getPass().equals(pass)){
				dao.delete(id);
				session.invalidate();
				mav.setViewName("redirect:/item/list.html");
				return mav;
			}else{
				throw new ErroPassException(" ");
			}
		}
		if(m.getId().equals("admin")){
			dao.delete(id);
			mav.setViewName("redirect:/member/admin.html");
			return mav;
		}
		else{
			throw new MismatchIdException();
		}
	}
	@RequestMapping("member/mypage")
	public ModelAndView mypage(String id, HttpServletRequest request){
		ModelAndView mav = info(id, request);
		List<Sale> saleList = saledao.list(id);
		for(Sale sale : saleList){
		List<SaleLine> saleLineList = saleLinedao.list(sale.getSaleId());
		for(SaleLine sline: saleLineList){
			Item item = 
					itemDao.getItemById(sline.getItemId());
			sline.setItem(item);
		}
		sale.setSaleLine(saleLineList);
		}
		mav.addObject("salelist",saleList);
		return mav;
	}
	@RequestMapping("member/mailForm")
	public ModelAndView mailForm(String[] chk){
		ModelAndView mav = new ModelAndView("member/mail");
		if(chk==null ||chk.length == 0){
			mav.setViewName("redirect:/member/admin.html");
			return mav;
		}
		List<String> memberlist = dao.getmember(chk);
		
		mav.addObject("memberlist",memberlist);
		
		return mav;
	}
	@RequestMapping("member/main")
	public ModelAndView mailSend(String recipient, String title, String mtype,String editor1){
		ModelAndView mav = new ModelAndView("redirect:/member/admin.html");
		sendAddMail(recipient,title,mtype,editor1);
		return mav;
	}
	private void sendAddMail(String recipient, String title, String mtype,String editor1) {
		System.out.println(recipient + "," + title + "," + mtype + "," + editor1);
		//���� ��ü ����
	MyAuthenticator auth = new MyAuthenticator("test@mesedu.co.kr","goodee@0205");
	//("������ ������� ","���� �ּ� ��й�ȣ")
		Properties prop = new Properties();
		//���� ���� ���� mail.pop3.host , 
		prop.put("mail.pop3.host","www.mesedu.co.kr");
		//�۽� ���� ����
		prop.put("mail.smtp.host","www.mesedu.co.kr");
		prop.put("mail.user","test");
		//�����»��
		prop.put("mail.from","test@mesedu.co.kr");
		prop.put("mail.debug","true");
		//�������θ� Ȯ�� -������ Ʈ�� !!!
		prop.put("mail.smtp.auth","true");
		prop.put("mail.smtp.port",587);//cafe24 , ���� ��Ʈ ��ȣ
		Session session = Session.getInstance(prop,auth);
		MimeMessage msg =new MimeMessage(session);
		
		try{
			
			msg.setFrom(new InternetAddress("test@mesedu.co.kr"));
			List<InternetAddress>addrs = new ArrayList<InternetAddress>();
			String[] emails =recipient.split(",");
			for(int i =0; i<emails.length; i++){
				try{
				addrs.add(new InternetAddress(new String(emails[i].getBytes("euc-kr"),"8859_1")));
				}catch(UnsupportedEncodingException e){
					e.printStackTrace();
				}
			}
			InternetAddress[] arr = new InternetAddress[emails.length];
			for(int i = 0; i<addrs.size();i++){
				arr[i]=addrs.get(i);
			}
			msg.setRecipients(Message.RecipientType.TO, arr);
			msg.setSentDate(new Date());
			//msg.setText(contents);
			InternetAddress from = new InternetAddress("test@mesedu.co.kr");
			msg.setFrom(from);
			msg.setRecipients(Message.RecipientType.TO, arr);//�������� ....
			msg.setSubject(title);
			msg.setContent(editor1,mtype);
			Transport.send(msg);
		}catch(MessagingException me){
			me.printStackTrace();
		}
		
	}
	
}


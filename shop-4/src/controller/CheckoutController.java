package controller;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.ItemDao;
import dao.MemberDao;
import dao.SaleDao;
import dao.SaleLineDao;
import exception.CartEmptyException;
import exception.LoginRequiredException;
import logic.Cart;
import logic.Item;
import logic.ItemSet;
import logic.Member;
import logic.Sale;
import logic.SaleLine;
import logic.Shop;


@Controller
public class CheckoutController {
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private MemberDao dao;
	@Autowired
	private SaleDao saledao;
	@Autowired
	private SaleLineDao saleLineDao;
	@Autowired
	private Shop shopService;

	@RequestMapping("checkout/checkout")
	public ModelAndView checkout(HttpSession session) {
		//현재 상태가 로그인 상태???
		Member loginMember = (Member) session.getAttribute("USER_KEY");
		if (loginMember == null) {
			throw new LoginRequiredException("로그인후 거래하세요 ");
		}
		Cart cart = (Cart) session.getAttribute("CART_KEY");
		if (cart == null || cart.isEmpty()) {
			throw new CartEmptyException("카트가 비어있습니다 .");
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("loginMember", loginMember);
		List<ItemSet> itemList = cart.getItemList();
		mav.addObject("itemList",itemList);
		Integer totalAmount = shopService.calculateTotalAmount(itemList);
		mav.addObject("totalAmount",totalAmount);
		return mav;
	}
	@RequestMapping("checkout/end")
	public ModelAndView end(HttpSession session){
		Member loginMember =(Member)session.getAttribute("USER_KEY");
		if(loginMember == null){
			throw new LoginRequiredException("로그인후 거래 하세요 ");
		}
		Cart cart = (Cart)session.getAttribute("CART_KEY");
		if(cart == null || cart.isEmpty()){
			throw new CartEmptyException("카트가 비어있습니다 .");
		}
		/*
		 * checkEnd : 1.주문 번호를 체번한다 .
		 *            2.sale 테이블의 레코드를 추가  -> 주문 정보
		 *            3.saleLine 테이블 레코드 추가 ->주문에 해당하는 상품정보
		 *  clearAll : 카트를 비워준다 .          
		 */
		Sale sale = shopService.checkEnd(loginMember,cart);
		Member member = dao.selectone(loginMember.getId());
		sendCon(member);
		//session의 카트 정보를 제거
		cart.clearAll(session);
		ModelAndView mav = new ModelAndView();
		mav.addObject("loginMember",loginMember);
		mav.addObject("sale",sale);
		
		
		List<SaleLine> itemList = sale.getSaleLine();
		Integer totalAmount = shopService.calculateTotalAmount2(itemList);
		mav.addObject("totalAmount",totalAmount);
		return mav;
		
	}
	private void sendCon(Member member) {
		MyAuthenticator auth = new MyAuthenticator("test@mesedu.co.kr","goodee@0205");
			Properties prop = new Properties(); 
			prop.put("mail.pop3.host","www.mesedu.co.kr");
			prop.put("mail.smtp.host","www.mesedu.co.kr");
			prop.put("mail.user","test");
			prop.put("mail.from","test@mesedu.co.kr");
			prop.put("mail.debug","false");
			prop.put("mail.smtp.auth","true");
			prop.put("mail.smtp.port",587);
			Session session = Session.getInstance(prop,auth);
			MimeMessage msg =new MimeMessage(session);
			List<Sale> saleList = saledao.list(member.getId());
			for(Sale sale : saleList){
				List<SaleLine> saleLineList = saleLineDao.list(sale.getSaleId());
				sale.setSaleLine(saleLineList);
				System.out.println(sale.getUpdateTime());
				for(SaleLine sline: saleLineList){
					Item item = 
						     itemDao.getItemById(sline.getItemId());
					sline.setItem(item);
					System.out.println(sline.getItem().getItemName()+sline.getQuantity()+"개");
				}
			   
			}
			
			try{
				
				msg.setFrom(new InternetAddress("test@mesedu.co.kr"));
				InternetAddress[] addrs = {new InternetAddress(member.getEmail())};
				msg.setRecipients(Message.RecipientType.TO, addrs);
				msg.setSentDate(new Date());
				InternetAddress from = new InternetAddress("test@mesedu.co.kr");
				InternetAddress to = new InternetAddress(member.getEmail());
				msg.setFrom(from);
				msg.addRecipient(Message.RecipientType.TO, to);//한명에게 보낼경우 사용
				msg.setSubject(member.getId()+"고객님의 구매 내역입니다 ");
				msg.setContent(
						"쇼핑몰을 이용해 주셔서 감사합니다 !!!!<br>"+member.getName()+
						"님의 구매 내역입니다 .<br>"
						
						,"text/html;charset=euc-kr");
				Transport.send(msg);
			}catch(MessagingException me){
				me.printStackTrace();
			}
			
		}
	//내부 클래스  
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
}

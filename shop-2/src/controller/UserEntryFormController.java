package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import logic.Shop;
import logic.User;
import utils.UserEntryValidator;
/*
 * @Component -@Controller
 *            -@SErvice
 *            @Repository
 */ 
@Controller
public class UserEntryFormController {
	private Shop shopService;
	private UserEntryValidator userEntryValidator;
	private MessageSource messageSource;

	public void setShopService(Shop shopService) {
		this.shopService = shopService;
	}

	public void setUserEntryValidator(UserEntryValidator userEntryValidator) {
		this.userEntryValidator = userEntryValidator;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	/*
	 * userEntry.html ��û�Ǹ�  ���� ��Ʈ�ѷ��� ȣ��ȴ� 
	 * ������  GET������� ���� ����  ȣ��Ǵ� �޼��� 
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String toUserEntryView() {
		return "userEntry"; //view����
		
	}
	/*��ȿ�� ������ �ʿ��ϴ� 
	 * veiw ���� ModelAttribute ����� ����Ǵ� �ż���
	 */
	@ModelAttribute
	public User setUpForm(){
		//MessageSourceAccessor : messages.properties �� ������ �������ִ� Ŭ���� 
		User user =new User();
		MessageSourceAccessor accessor =
				new MessageSourceAccessor(this.messageSource);
		user.setUserId(accessor.getMessage("user.userId.default"));
		user.setUserName(accessor.getMessage("user.userName.default"));
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
       
        	
				try {
					user.setBirthDay(dateFormat.parse("2000-01-01"));
				} catch (java.text.ParseException e) {
					
					e.printStackTrace();
				}
			
       
		return user;
	}
	//����ȯ + �Է��׸� ����
	@InitBinder
	public void InitBinder(WebDataBinder binder)throws Exception{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//dateFormat.setLenient(false): ���� �߻� ����
		dateFormat.setLenient(false);
		/*
		 * Date.class :Date Ÿ������ ����ȯ�� !
		 * "birthDay" :�Ķ���� �� birthDay 
		 *             Ÿ���� ���� �ʴ´ٸ� typeMismatch.birthDay �޽��� �ڵ�� ���� ���ش� .
		 *  new CustomDateEditor
		 *   1.�Ķ���� : dateFormat��ü ���Ĵ�� �������� 
		 *   false : ���ڿ� , null�ΰ��� ���������
		 *   true : ����� !!!!
		 *   
		 */
		binder.registerCustomEditor(Date.class, "birthDay", new CustomDateEditor(dateFormat, false));
	}
	//url: userEntry.html ���� +POST ����� ��� ȣ��Ǵ� �޼��� 
	//user ��ü�� ������Ƽ�� �Ķ���͸� ���ؼ� �Ķ���Ͱ��� user��ü�� ����  
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView onSubmit
	//�Ķ���ʹ� user�ȿ� ������־��ش� .
	//bindingResult : ��ȿ�� �������� ��� �Ǵ� ��ü 
	//                error ������ �����ϰ��ִ°�ü 
	(User user, BindingResult bindingResult)throws Exception{
		//��ȿ���˻� : �Է� �׸�˻�  (userEntryValidator)
		userEntryValidator.validate(user, bindingResult);
		ModelAndView mav = new ModelAndView();
		//�Է¿� ������ �߻��Ѱ�� 
		if(bindingResult.hasErrors()){
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		try{
			shopService.entryUser(user);
			mav.setViewName("userEntrySuccess");//view �̸� ����
			mav.addObject("user",user);//��ü���� ��� user�� view���� �Ķ���ͷ� ����Ҽ��ְ� �� 
		}catch(DataIntegrityViolationException e){
			bindingResult.reject("error.duplicate.user");
			mav.getModel().putAll(bindingResult.getModel());
		}
		return mav;
	}
}

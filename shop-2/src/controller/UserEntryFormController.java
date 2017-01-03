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
	 * userEntry.html 요청되면  현재 컨트롤러가 호출된다 
	 * 하지만  GET방식으로 들어올 경우는  호출되는 메서드 
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String toUserEntryView() {
		return "userEntry"; //view결정
		
	}
	/*유효성 검증에 필요하다 
	 * veiw 단의 ModelAttribute 실행시 실행되는 매서드
	 */
	@ModelAttribute
	public User setUpForm(){
		//MessageSourceAccessor : messages.properties 의 내용을 읽을수있는 클래스 
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
	//형변환 + 입력항목 검증
	@InitBinder
	public void InitBinder(WebDataBinder binder)throws Exception{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//dateFormat.setLenient(false): 오류 발생 무시
		dateFormat.setLenient(false);
		/*
		 * Date.class :Date 타입으로 형변환해 !
		 * "birthDay" :파라미터 값 birthDay 
		 *             타입이 맞지 않는다면 typeMismatch.birthDay 메시지 코드로 설정 해준다 .
		 *  new CustomDateEditor
		 *   1.파라미터 : dateFormat객체 형식대로 포멧지정 
		 *   false : 빈문자열 , null인경우는 허용하지마
		 *   true : 허용해 !!!!
		 *   
		 */
		binder.registerCustomEditor(Date.class, "birthDay", new CustomDateEditor(dateFormat, false));
	}
	//url: userEntry.html 형태 +POST 방식인 경우 호출되는 메서드 
	//user 객체에 프러퍼티와 파라미터를 비교해서 파라미터값을 user객체가 저장  
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView onSubmit
	//파라미터는 user안에 다집어넣어준다 .
	//bindingResult : 유효성 검증에서 사용 되는 객체 
	//                error 정보를 저장하고있는객체 
	(User user, BindingResult bindingResult)throws Exception{
		//유효성검사 : 입력 항목검사  (userEntryValidator)
		userEntryValidator.validate(user, bindingResult);
		ModelAndView mav = new ModelAndView();
		//입력에 오류가 발생한경우 
		if(bindingResult.hasErrors()){
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		try{
			shopService.entryUser(user);
			mav.setViewName("userEntrySuccess");//view 이름 설정
			mav.addObject("user",user);//객체들이 담긴 user를 view에서 파라미터로 사용할수있게 함 
		}catch(DataIntegrityViolationException e){
			bindingResult.reject("error.duplicate.user");
			mav.getModel().putAll(bindingResult.getModel());
		}
		return mav;
	}
}

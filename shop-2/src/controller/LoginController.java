package controller;

import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logic.Shop;
import logic.User;

public class LoginController {
	private Shop shopService;
	private Validator loginValidator;
	private MessageSource messageSource;

	public void setShopService(Shop shopService) {
		this.shopService = shopService;
	}

	public void setLoginValidator(Validator loginValidator) {
		this.loginValidator = loginValidator;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	// 로그인 폼 처리
	//return type String : view만 결정 data는 필요없는경우 
	@RequestMapping(method = RequestMethod.GET)
	public String toLoginView() {
         return "login";//view의 이름
	}
	@ModelAttribute
	public User setUpForm(){
		return new User();
	
	}
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(User user, BindingResult bindingResult)throws Exception{
		loginValidator.validate(user, bindingResult);
		ModelAndView mav = new ModelAndView();
		if(bindingResult.hasErrors()){
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		//user 객체           : 파라미터 값의 정보가 저장 되어
		//loginUser 객체: id password로 db에서 검색된 유저의 정보 
		try{
			User loginUser = shopService.getUserByIdAndPw
					(user.getUserId(), user.getPassword());
			//view 를 지정
			mav.setViewName("loginSuccess");
			
			//data 지정
			mav.addObject("loginUser",loginUser);
			
			//해당 레코드 없음
		}catch(EmptyResultDataAccessException e){
			bindingResult.reject("error.login.id");
			mav.getModel().putAll(bindingResult.getModel());
		}
		return mav;
		
	}
}

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

	// �α��� �� ó��
	//return type String : view�� ���� data�� �ʿ���°�� 
	@RequestMapping(method = RequestMethod.GET)
	public String toLoginView() {
         return "login";//view�� �̸�
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
		//user ��ü           : �Ķ���� ���� ������ ���� �Ǿ�
		//loginUser ��ü: id password�� db���� �˻��� ������ ���� 
		try{
			User loginUser = shopService.getUserByIdAndPw
					(user.getUserId(), user.getPassword());
			//view �� ����
			mav.setViewName("loginSuccess");
			
			//data ����
			mav.addObject("loginUser",loginUser);
			
			//�ش� ���ڵ� ����
		}catch(EmptyResultDataAccessException e){
			bindingResult.reject("error.login.id");
			mav.getModel().putAll(bindingResult.getModel());
		}
		return mav;
		
	}
}

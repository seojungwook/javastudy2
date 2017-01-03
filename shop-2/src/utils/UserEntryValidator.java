package utils;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import logic.User;

//유효성 검증에 관련있는 클래스 
public class UserEntryValidator implements Validator{
	//supports의 결과가 참일경우만 유효성 검사를 할것이다 .
	@Override
	public boolean supports(Class<?> cls) {
		return User.class.isAssignableFrom(cls);
	}
    
	@Override
	public void validate(Object command, Errors errors) {
		User user =(User)command;
		if(!StringUtils.hasLength(user.getUserId())){
			errors.rejectValue("userId", "error.required");
		}
		if(!StringUtils.hasLength(user.getPassword())){
			errors.rejectValue("password", "error.required");
		}
		if(!StringUtils.hasLength(user.getUserName())){
			errors.rejectValue("userName", "error.required");
		}
		if(!StringUtils.hasLength(user.getPostCode())){
			errors.rejectValue("postCode", "error.required");
		}
		if(!StringUtils.hasLength(user.getAddress())){
			errors.rejectValue("address", "error.required");
		}
		if(!StringUtils.hasLength(user.getEmail())){
			errors.rejectValue("email", "error.required");
		}
		//errors.hasErrors() : 입력된 error 가있나?
		if(errors.hasErrors()){
			errors.reject("error.input.user");
		}
	}

}

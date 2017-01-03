package utils;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import logic.User;

public class LoginValidator implements Validator{
/*
 * 결과가 true인 클래스 타입만 유효성 검증을 함.
 * 유효성 검증되는 클래스타입을 지정 기능
 */
	@Override
	public boolean supports(Class<?> cls) {
	 return User.class.isAssignableFrom(cls);//User클래스에 형변환이 됨????
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User user =(User)obj;//supports에서 이미 검증이 끝났다 .가능
		//messages.properties파일 참조
		if(!StringUtils.hasLength(user.getUserId())){
			errors.rejectValue("userId", "error.required");
		}
		if(!StringUtils.hasLength(user.getPassword())){
			errors.rejectValue("password", "error.required");
		}
		if(errors.hasErrors()){//에러가 있다면 무조건 출력 
			errors.reject("error.login.id");
			errors.reject("error.input.user");
			
		}
	}

}

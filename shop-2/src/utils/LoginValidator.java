package utils;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import logic.User;

public class LoginValidator implements Validator{
/*
 * ����� true�� Ŭ���� Ÿ�Ը� ��ȿ�� ������ ��.
 * ��ȿ�� �����Ǵ� Ŭ����Ÿ���� ���� ���
 */
	@Override
	public boolean supports(Class<?> cls) {
	 return User.class.isAssignableFrom(cls);//UserŬ������ ����ȯ�� ��????
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User user =(User)obj;//supports���� �̹� ������ ������ .����
		//messages.properties���� ����
		if(!StringUtils.hasLength(user.getUserId())){
			errors.rejectValue("userId", "error.required");
		}
		if(!StringUtils.hasLength(user.getPassword())){
			errors.rejectValue("password", "error.required");
		}
		if(errors.hasErrors()){//������ �ִٸ� ������ ��� 
			errors.reject("error.login.id");
			errors.reject("error.input.user");
			
		}
	}

}

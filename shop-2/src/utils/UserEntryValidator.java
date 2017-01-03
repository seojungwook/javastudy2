package utils;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import logic.User;

//��ȿ�� ������ �����ִ� Ŭ���� 
public class UserEntryValidator implements Validator{
	//supports�� ����� ���ϰ�츸 ��ȿ�� �˻縦 �Ұ��̴� .
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
		//errors.hasErrors() : �Էµ� error ���ֳ�?
		if(errors.hasErrors()){
			errors.reject("error.input.user");
		}
	}

}

package dao.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import logic.User;

public interface UserMapper {
	@Select("select * from useraccount where userId = #{userId} and password = #{password} ")
	User getUserByIdAndPw(Map<String, String> paramMap);

	@Insert("insert into userAccount (userId, userName, password, postCode, address, email, job, birthDay) values (#{userId}, #{userName}, #{password}, #{postCode}, #{address}, #{email}, #{job}, #{birthDay})")
	void createUser(User user);

	

}

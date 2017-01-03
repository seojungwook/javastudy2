package dao;

import logic.User;

public interface UserDao {

	User getUserByIdAndPw(String id, String pass);

	void createUser(User user);

}

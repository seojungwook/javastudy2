package dao;

import logic.User;

public interface UserDao {

	User getUserByIdAndPw(String userId, String password);

	void createUser(User user);

}

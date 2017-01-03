package dao;

import logic.User;

public interface UserDao {
	void create(User user);

	User loginuser(String userId, String password);
}

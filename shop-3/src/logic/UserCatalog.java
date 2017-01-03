package logic;

public interface UserCatalog {

	User getUserByIdAndPw(String userId, String password);

	void createUser(User user);

}

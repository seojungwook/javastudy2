package logic;

public interface UserCatalog {

	User getUserByIdAndPw(String id, String pass);

	void createUser(User user);

}

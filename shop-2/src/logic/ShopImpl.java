package logic;

public class ShopImpl implements Shop{
private UserCatalog userCatalog;

	public void setUserCatalog(UserCatalog userCatalog) {
	this.userCatalog = userCatalog;
}

	@Override
	public void entryUser(User user) {
        userCatalog.entryUser(user);		
		
	}

	@Override
	public User getUserByIdAndPw(String userId, String password) {
		User user=userCatalog.getUserByIdAndPw(userId,password);
		return user;
	}

}

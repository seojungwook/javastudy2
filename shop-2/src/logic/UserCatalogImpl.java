package logic;

import dao.UserDao;

public class UserCatalogImpl implements UserCatalog{
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public void entryUser(User user) {
	userDao.create(user);
		
	}
	@Override
	public User getUserByIdAndPw(String userId, String password) {
		User user= userDao.loginuser(userId,password);
		return user;
	}

}

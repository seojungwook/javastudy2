package logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;
@Service
public class UserCatalogImpl implements UserCatalog{
@Autowired
private UserDao userDao;
	@Override
	public User getUserByIdAndPw(String id, String pass) {
		
		return userDao.getUserByIdAndPw(id,pass);
	}
	@Override
	public void createUser(User user) {
		userDao.createUser(user);
		
	}

}

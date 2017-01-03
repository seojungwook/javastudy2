package dao;

import java.util.HashMap;
import java.util.Map;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;


import dao.mapper.UserMapper;
import logic.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Override
	public User getUserByIdAndPw(String userId, String password) {
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", userId);
		paramMap.put("password", password);
		return sqlSession.getMapper(UserMapper.class).getUserByIdAndPw(paramMap);
	}

	@Override
	public void createUser(User user) {
		sqlSession.getMapper(UserMapper.class).createUser(user);
	}

	
}

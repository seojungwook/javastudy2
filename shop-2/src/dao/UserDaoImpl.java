package dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import logic.User;
public class UserDaoImpl implements UserDao{
     private NamedParameterJdbcTemplate template;
     public void setDataSource(DataSource dataSource) {
		template=
				new NamedParameterJdbcTemplate(dataSource);
	}
	@Override
	public void create(User user) {
		SqlParameterSource paramSource=
				new BeanPropertySqlParameterSource(user);
		String sql = "insert into useraccount "
				+"(userId, username, password, postcode,"
				+"address, email ,job, birthday)"
				+"values(:userId, :userName, :password,"
				+":postCode, :address, :email, :job, :birthDay)";
		template.update(sql, paramSource);
		//�ߺ� �߻��� 
		//DataIntegrityViolationException ���� �߻�
	}
	@Override
	public User loginuser(String userId, String password) {
		String sql = "select * from useraccount where userid = :userId and password = :password";
		//RowMapper : Ŀ���� ����� �����ϴ� ��ü�� �����ϴ� ��ü
		RowMapper<User> mapper= new BeanPropertyRowMapper<User>(User.class);
		Map<String, String> paramMap = new HashMap<String , String>();
		paramMap.put("userId",userId);
		paramMap.put("password",password);
		User user=new User();
		//�ش� ���ڵ尡 ���� ���� �ʴ´ٸ� 
		//EmptyResultDataAccessException ���� �߻� 
		user=template.queryForObject(sql, paramMap, mapper);
		return user;
	}

}

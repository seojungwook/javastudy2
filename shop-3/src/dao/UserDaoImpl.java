package dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import logic.User;

@Repository
public class UserDaoImpl implements UserDao {
	private NamedParameterJdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public User getUserByIdAndPw(String userId, String password) {
		String sql = "select * from useraccount where userid = :userId and password = :password ";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", userId);
		paramMap.put("password", password);
		RowMapper<User> mapper = new BeanPropertyRowMapper<User>(User.class);
		return template.queryForObject(sql, paramMap, mapper);
	}

	@Override
	public void createUser(User user) {
		String sql = "insert into userAccount (userId, userName, password, postCode, address, email, job, birthDay) values (:userId, :userName, :password, :postCode, :address, :email, :job, :birthDay)";
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
		template.update(sql, paramSource);
	}

	
}

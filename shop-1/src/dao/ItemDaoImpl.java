package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;//커낵션 객체

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import logic.Item;

public class ItemDaoImpl implements ItemDao {
	private NamedParameterJdbcTemplate template;

	public void setDataSource(DataSource dataSource) {
		this.template = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Item> findAll() {// DB와 이름을 맞춰준다(알리아스 사용)
		String sql = "select id itemId ,name itemname, price,description, picture pictureurl from item";
		//RowMapper :
		RowMapper<Item> mapper=
				new BeanPropertyRowMapper<Item>(Item.class);
		return template.query(sql, mapper);
	}

	@Override
	public Item findById(Integer itemId) {
		String sql ="select id itemId ,name itemname, price,description, picture pictureurl from item where id=:itemId";
		RowMapper<Item> mapper=
				new BeanPropertyRowMapper<Item>(Item.class);
		Map<String, Integer>paramMap = new HashMap<String, Integer>();
		paramMap.put("itemId", itemId);
		return template.queryForObject(sql, paramMap, mapper);
	}

}

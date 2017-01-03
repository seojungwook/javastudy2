package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;//Ŀ���� ��ü

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
	public List<Item> findAll() {// DB�� �̸��� �����ش�(�˸��ƽ� ���)
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

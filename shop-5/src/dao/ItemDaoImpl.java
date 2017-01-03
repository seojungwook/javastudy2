package dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import logic.Item;

@Repository
public class ItemDaoImpl implements ItemDao {
	private NamedParameterJdbcTemplate template;

	@Autowired
	public void setDataDource(DataSource dataSource) {
		template = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Item> getItemList() {
		String sql = "select id itemId, name itemName, price, description, picture pictureUrl from item order by id";
		RowMapper<Item> mapper = new BeanPropertyRowMapper<Item>(Item.class);
		return template.query(sql, mapper);
	}

	@Override
	public void addItem(Item item) {
		String sql = "insert into item (id,name,price,description,picture) values(:itemId,:itemName,:price,:description,:pictureUrl)";
		item.setPictureUrl(item.getPicture().getOriginalFilename());
		SqlParameterSource parameterSource = 
				new BeanPropertySqlParameterSource(item);
		 
		template.update(sql,parameterSource);
	}

}

package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import logic.Item;

@Repository
public class ItemDaoImpl implements ItemDao {
	private NamedParameterJdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		template = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Item> findAll() {
		String sql ="select id itemId,name itemName,price,description,picture pictureUrl from item order by id";
		RowMapper<Item> mapper = new BeanPropertyRowMapper<Item>(Item.class);
		return template.query(sql, mapper);
	}

	@Override
	public Item getItemById(Integer itemId) {
		
		String sql="select id itemId,name itemName,price,description,picture pictureUrl from item where id=:itemId";
		RowMapper<Item> mapper = new BeanPropertyRowMapper<Item>(Item.class);
		Map<String,Integer> paramMap = new HashMap<String,Integer>();
		
		paramMap.put("itemId",itemId);
		return template.queryForObject(sql, paramMap, mapper);
	}

	@Override
	public List<Item> searchList(String itemName) {
		String sql="select id itemId,name itemName,price,description,picture pictureUrl from item where name like :itemName order by id";
		RowMapper<Item> mapper = new BeanPropertyRowMapper<Item>(Item.class);
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("itemName","%"+itemName+"%");
		return template.query(sql, paramMap, mapper);
	}

	@Override
	public void create(Item item) {
		item.setItemId(getMaxItemId());
		String sql="insert into item (id, name, price, description, picture) values (:itemId, :itemName, :price, :description, :pictureUrl)";
		 item.setPictureUrl(item.getPicture().getOriginalFilename());
		 SqlParameterSource paramSource = new BeanPropertySqlParameterSource(item);
		 template.update(sql, paramSource);
	}

	public Integer getMaxItemId() {
		String sql="select nvl(max(id),0) from item";
		int i = template.queryForObject(sql, new HashMap<String,Object>(),Integer.class);
		return ++i;
	}

	@Override
	public void update(Item item) {
		String sql="update item set name=:itemName,price=:price,picture=:pictureUrl,description=:description where id=:itemId";
		 if(item.getPicture().getOriginalFilename()!=null &&
				 !item.getPicture().getOriginalFilename().equals("")){
			 item.setPictureUrl(item.getPicture().getOriginalFilename());
		 }
		 SqlParameterSource paramSource = new BeanPropertySqlParameterSource(item);
		 template.update(sql, paramSource);
	}

	@Override
	public void delete(Integer itemId) {
		String sql ="delete from item where id=:itemId";
		Map <String,Integer> paramMap= new HashMap<String,Integer>();
		paramMap.put("itemId", itemId);
		template.update(sql, paramMap);
	}
   
	

}

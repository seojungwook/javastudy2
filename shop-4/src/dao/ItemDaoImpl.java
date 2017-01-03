package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import dao.mapper.ItemMapper;
import logic.Item;

@Repository
public class ItemDaoImpl implements ItemDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	private final String NS="dao.mapper.ItemMapper.";
		@Override
	public List<Item> findAll() {
			return sqlSession.selectList(NS+"getItem");
	}

	@Override
	public Item getItemById(Integer itemId) {
		
		Map<String,Integer> paramMap = new HashMap<String,Integer>();
		paramMap.put("itemId",itemId);
		return sqlSession.selectOne(NS+"getItem" , paramMap);
	}

	@Override
	public List<Item> searchList(String itemName) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("itemName","%"+itemName+"%");
	 	return sqlSession.selectList(NS+"getItem",paramMap);
	}

	@Override
	public void create(Item item) {
		item.setItemId(getMaxItemId());
		 item.setPictureUrl(item.getPicture().getOriginalFilename());
		 sqlSession.getMapper(ItemMapper.class).create(item);
	}

	public Integer getMaxItemId() {
		int i = sqlSession.getMapper(ItemMapper.class).itemMaxId();
		return ++i;
	}

	@Override
	public void update(Item item) {
		 if(item.getPicture().getOriginalFilename()!=null &&
				 !item.getPicture().getOriginalFilename().equals("")){
			 item.setPictureUrl(item.getPicture().getOriginalFilename());
		 }
		 sqlSession.getMapper(ItemMapper.class).update(item);
	}

	@Override
	public void delete(Integer itemId) {
		Map <String,Integer> paramMap= new HashMap<String,Integer>();
		paramMap.put("itemId", itemId);
		sqlSession.getMapper(ItemMapper.class).delete(itemId);
	}
   
	

}

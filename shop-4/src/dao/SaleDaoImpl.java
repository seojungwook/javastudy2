package dao;

import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import dao.mapper.SaleMapper;
import logic.Sale;
@Repository
public class SaleDaoImpl implements SaleDao{
@Autowired
private SqlSessionTemplate sqlSession;
	@Override
	public void create(Sale sale) {
	    sqlSession.getMapper(SaleMapper.class).create(sale);
	}

	@Override
	public int findMaxSaleId() {
		
		int val = sqlSession.getMapper(SaleMapper.class).findMaxSaleId();
		return val;
	}

	@Override
	public List<Sale> list(String id) {
		
		return sqlSession.getMapper(SaleMapper.class).list(id);
	}

}

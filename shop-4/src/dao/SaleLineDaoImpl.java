package dao;

import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import dao.mapper.SaleLineMapper;
import logic.SaleLine;
@Repository
public class SaleLineDaoImpl implements SaleLineDao{
	
	@Autowired
	SqlSessionTemplate sqlSession ;
	@Override
	public void create(SaleLine saleLine) {
	sqlSession.getMapper(SaleLineMapper.class).create(saleLine);
	}
	@Override
	public List<SaleLine> list(Integer saleId) {
		return sqlSession.getMapper(SaleLineMapper.class).list(saleId);
	}

}

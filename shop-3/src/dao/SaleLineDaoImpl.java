package dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import logic.SaleLine;
@Repository
public class SaleLineDaoImpl implements SaleLineDao{
	private NamedParameterJdbcTemplate template;
	@Autowired
	public void setDataSource(DataSource dataSource){
		template = new NamedParameterJdbcTemplate(dataSource);
	}
	@Override
	public void create(SaleLine saleLine) {
	String sql="insert into saleLine (saleid, salelineid, itemid, quantity, updatetime) values(:sale.saleId,:saleLineId,:item.itemId,:quantity,:updateTime)";
	SqlParameterSource paramSource = new BeanPropertySqlParameterSource(saleLine);
	template.update(sql, paramSource);
	}

}

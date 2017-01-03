package dao;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import logic.Sale;
@Repository
public class SaleDaoImpl implements SaleDao{
private NamedParameterJdbcTemplate template;

@Autowired
public void setDataSource(DataSource dataSource){
	template = new NamedParameterJdbcTemplate(dataSource);
}
	@Override
	public void create(Sale sale) {
		String sql ="insert into sale (saleid , userid , updatetime) values(:saleId , :user.userId, :updateTime)";
	    SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sale);
	    template.update(sql, paramSource);
	}

	@Override
	public int findMaxSaleId() {
		String sql ="select nvl(max(saleId),0) from sale";
		int val = template.queryForObject
				           (sql, new HashMap<String , Object>(), Integer.class);
		return val;
	}

}

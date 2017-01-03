package dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import logic.SaleLine;

public interface SaleLineMapper {
	@Insert("insert into saleLine (saleid, salelineid, itemid, quantity, updatetime) values(#{sale.saleId},#{saleLineId},#{item.itemId},#{quantity},#{updateTime})")
	void create(SaleLine saleLine);

	@Select("select * from saleline where saleId=#{value}")
	List<SaleLine> list(Integer saleId);

}

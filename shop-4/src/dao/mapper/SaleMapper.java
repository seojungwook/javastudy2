package dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import logic.Sale;

public interface SaleMapper {
	@Insert("insert into sale (saleid , userId , updatetime) values(#{saleId} , #{member.id}, #{updateTime})")
	void create(Sale sale);

	@Select("select nvl(max(saleId),0) from sale")
	int findMaxSaleId();
	
    @Select("select * from sale where userid=#{value}")
	List<Sale> list(String id);

}

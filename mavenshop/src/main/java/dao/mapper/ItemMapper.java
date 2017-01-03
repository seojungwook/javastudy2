package dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.Item;

public interface ItemMapper {

	@Insert("insert into item (id, name, price, description, picture) values (#{itemId}, #{itemName}, #{price}, #{description}, #{pictureUrl})")
	void create(Item item);

	@Select("select nvl(max(id),0) from item")
	int itemMaxId();

	@Delete("delete from item where id=#{itemId}")
	void delete(Integer itemId);

	@Update("update item set name=#{itemName},price=#{price},picture=#{pictureUrl},description=#{description} where id=#{itemId}")
	void update(Item item);

}

package dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.Member;

public interface MemberMapper {
	@Insert("insert into member (id,pass,name,birthDay,gender,email,pictureUrl,postCode,address) values (#{id},#{pass}"
			+ ",#{name},#{birthDay},#{gender},#{email},#{pictureUrl},#{postCode},#{address})")
	int insert(Member member);

	@Select("select * from member")
	List<Member> memberlist();

	@Select("select * from member where id=#{id}")
	Member selectone(String id);

	@Update("update member set pass=#{pass},name=#{name},birthDay=#{birthDay},gender=#{gender},email=#{email},pictureUrl=#{pictureUrl},postCode=#{postCode},address=#{address} where id=#{id}")
	void updatemember(Member member);

	@Delete("delete from member where id=#{value}")
	void delete(String id);


}

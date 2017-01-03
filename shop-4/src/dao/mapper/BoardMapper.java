package dao.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.Board;

public interface BoardMapper {
	@Select("select count(*) from board")
	int boardCount();

	@Select("select num,id,pass,subject,content,file1 fileUrl,regDate,ref,refLevel,refStep from board where num=#{num}")
	Board selectOne(Integer num);

	@Insert("insert into board values(#{num},#{id},#{pass},#{subject},#{content},#{fileUrl},sysdate,0,#{num},0,0,0)")
	void boardWrite(Board board);
	@Select("select nvl(max(num),0) from board")
	Integer getMaxNum();
    @Update("update board set readcnt = readcnt+1 where num=#{num}")
	void readCntAdd(Integer num);
    @Insert("insert into board values(#{num},#{id},#{pass},#{subject},#{content},#{fileUrl},sysdate,0,#{ref},#{refStep},#{refLevel},0)")
	void rewrite(Board board);
    @Delete("delete from board where num=#{num}")
	void delete(Board board);
    @Update("update board set content =#{content},subject=#{subject},file1=#{fileUrl} where num=#{num}")
	void update(Board board);

    @Select("select nvl(count(*),0) from board where ${searchtype} like '%${searchContent}%'")
	int searchCount(Map<String, String> map);


	
}

package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.MemberMapper;
import logic.Member;

@Repository
public class MemberImpl implements MemberDao{
    @Autowired
    private SqlSessionTemplate sqlSession;
    private final String NS = "dao.mapper.MemberMapper.";
	@Override
	public int memberInsert(Member member) {
		
		return sqlSession.getMapper(MemberMapper.class).insert(member);
	}
	@Override
	public Member getmemberinfo(String id, String pass) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("id",id );
		map.put("pass",pass );
		return sqlSession.selectOne(NS+"login",map);
	}
	@Override
	public List<Member> memberList() {
		return sqlSession.getMapper(MemberMapper.class).memberlist();
	}
	@Override
	public Member selectone(String id) {
		
		return sqlSession.getMapper(MemberMapper.class).selectone(id);
	}
	@Override
	public void updatemember(Member member) {
		sqlSession.getMapper(MemberMapper.class).updatemember(member);
		
	}
	@Override
	public void delete(String id) {
		sqlSession.getMapper(MemberMapper.class).delete(id);
		
	}
	@Override
	public List<String> getmember(String[] chk) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("chk",chk);
		return sqlSession.selectList(NS+"emaillist",map);
	}
	

}

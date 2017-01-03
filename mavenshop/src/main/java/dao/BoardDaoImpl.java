package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.BoardMapper;
import logic.Board;
@Repository
public class BoardDaoImpl implements BoardDao{
@Autowired
private SqlSessionTemplate sqlSession;
private final String NS="dao.mapper.BoardMapper.";
	@Override
	public int boardCount() {
		return sqlSession.getMapper(BoardMapper.class).boardCount();
		
	}

	@Override
	public List<Board> getBoardList(Integer pageNum, int limit) {
		int startrow = (pageNum -1) * limit + 1;
		int endrow = startrow + limit -1;
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("start",startrow);
		map.put("end",endrow);
		return sqlSession.selectList(NS+"getBoard",map);
		
	}

	@Override
	public Board selectOne(Integer num, Integer pageNum) {
		
		return sqlSession.getMapper(BoardMapper.class).selectOne(num);
	}

	@Override
	public void boardWrite(Board board, HttpServletRequest request) {
		Integer i = sqlSession.getMapper(BoardMapper.class).getMaxNum();
		
		board.setNum(i+1);
		board.setRef(i);
		
		
		sqlSession.getMapper(BoardMapper.class).boardWrite(board);
	}

	@Override
	public void readCntAdd(Integer num) {
		sqlSession.getMapper(BoardMapper.class).readCntAdd(num);
		
	}

	@Override
	public void rewrite(Board board) {
		Integer i = sqlSession.getMapper(BoardMapper.class).getMaxNum();
		board.setRef(board.getRef());
		board.setRefLevel(board.getRefLevel()+1);
		board.setNum(i+1);
		board.setSubject("RE:"+board.getSubject());
		board.setFileUrl("");
		board.setRefStep(board.getRefStep()+1);
	    sqlSession.getMapper(BoardMapper.class).rewrite(board);
	}

	@Override
	public void delete(Board board) {
		sqlSession.getMapper(BoardMapper.class).delete(board);
		
	}

	@Override
	public void update(Board board) {
		sqlSession.getMapper(BoardMapper.class).update(board);
		
	}

	@Override
	public List<Board> search(String searchtype, String searchContent,Integer pageNum) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		int startrow = (pageNum -1) * 10 + 1;
		int endrow = startrow + 10 -1;
		map.put("start",startrow);
		map.put("end",endrow);
		map.put("searchtype",searchtype);
		map.put("searchContent",searchContent);
		return sqlSession.selectList(NS+"seojw",map);
		
	}

	@Override
	public int searchCount(String searchtype, String searchContent) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("searchtype",searchtype);
		map.put("searchContent",searchContent);
		return sqlSession.getMapper(BoardMapper.class).searchCount(map);
	}


}

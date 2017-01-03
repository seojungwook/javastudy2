package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import dao.BoardDao;
@Service
public class BoardCatalogImpl implements BoardCatalog{
    @Autowired
    private BoardDao boardDao;
	@Override
	public int boardCount() {
		 
		return boardDao.boardCount();
	}

	@Override
	public List<Board> getboardlist(Integer pageNum, int limit) {
		
		return boardDao.getBoardList(pageNum,limit);
	}

	@Override
	public Board selectOne(Integer num,Integer pageNum) {
		boardDao.readCntAdd(num);//조회수 증가
		return boardDao.selectOne(num,pageNum);
	}

	@Override
	public void boardWrite(Board board, HttpServletRequest request) {
		boardDao.boardWrite(board,request);
		
	}

	@Override
	public void rewrite(Board board) {
		boardDao.rewrite(board);
	}

	@Override
	public void delete(Board board) {
		boardDao.delete(board);
		
	}

	@Override
	public void update(Board board) {
		boardDao.update(board);
		
	}

	@Override
	public List<Board> search(String searchtype, String searchContent, Integer pageNum) {
		return boardDao.search(searchtype, searchContent,pageNum);
	}

	

}

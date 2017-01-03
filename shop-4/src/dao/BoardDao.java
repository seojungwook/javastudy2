package dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;

import logic.Board;

public interface BoardDao {

	int boardCount();

	List<Board> getBoardList(Integer pageNum, int limit);

	Board selectOne(Integer num, Integer pageNum);

	void boardWrite(Board board, HttpServletRequest request);

	void readCntAdd(Integer num);

	void rewrite(Board board);

	void delete(Board board);

	void update(Board board);

	List<Board> search(String searchtype, String searchContent,Integer pageNum);

	int searchCount(String searchtype, String searchContent);



}

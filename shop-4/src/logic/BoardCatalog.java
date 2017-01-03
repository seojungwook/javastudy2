package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;

public interface BoardCatalog {

	int boardCount();

	List<Board> getboardlist(Integer pageNum, int limit);

	Board selectOne(Integer num, Integer pageNum);

	void boardWrite(Board board, HttpServletRequest request);

	void rewrite(Board board);

	void delete(Board board);

	void update(Board board);

	List<Board> search(String searchtype, String searchContent, Integer pageNum);


   
}

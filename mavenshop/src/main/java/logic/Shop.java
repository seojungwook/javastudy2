package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.web.multipart.MultipartFile;

import logic.Board;
import logic.Cart;

public interface Shop {

	Item getItemById(Integer itemId);

	Cart getCart();

	Member getUserByIdAndPw(String id, String pass);

	void createUser(Member user);

	Integer calculateTotalAmount(List<ItemSet> itemList);

	Sale checkEnd(Member loginUser, Cart cart);

	Integer calculateTotalAmount2(List<SaleLine> itemList);

	int boardCount();

	

	List<logic.Board> getBoardList(Integer pageNum, int limit);

	Board selectOne(Integer num, Integer pageNum);

	void boardWrite(Board board, HttpServletRequest request);
	
	void upload(MultipartFile upload , HttpServletRequest request);

	void rewrite(Board board);

	void delete(Board board);

	void update(Board board, HttpServletRequest request);


	List<Board> search(String searchtype, String searchContent, Integer pageNum);

	




	

}

package logic;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dao.BoardDao;
//@component + ���񽺱��
@Service
public class ShopImpl implements Shop{
    @Autowired
	private ItemCatalog itemCatalog;
    @Autowired
    private SaleCatalog saleCatalog;
    @Autowired
    private BoardCatalog boardCatalog;
	@Override
	public Item getItemById(Integer itemId) {
		
		return itemCatalog.getItemById(itemId);
	}

	@Override
	public Cart getCart() {
		
		return new Cart();
	}

//	@Override
//	public User getUserByIdAndPw(String id, String pass) {
//		
//		return userCatalog.getUserByIdAndPw(id,pass);
//	}
//
//	@Override
//	public void createUser(User user) {
//		 userCatalog.createUser(user);
//		
//	}

	@Override
	public Integer calculateTotalAmount(List<ItemSet> itemList) {
		int total =0;
		for(ItemSet is : itemList){
			total += 
					is.getItem().getPrice() * is.getQuantity();
		}
		return total;
	}

	@Override
	public Sale checkEnd(Member loginUser, Cart cart) {
		//loginUser��cart ��ü�� �����͸� ������ sale��ü �ϼ�
		Sale sale = createSale(loginUser , cart);
		insertSale(sale);
		return sale;
	}

	private Sale createSale(Member loginUser, Cart cart) {
		Sale sale = new Sale();
		sale.setSaleId(getMaxSaleId());
		sale.setMember(loginUser);
		Timestamp currentTime = getCurrentTime();
		sale.setUpdateTime(currentTime);
		List<ItemSet> itemList = cart.getItemList();
		for(int i=0; i<itemList.size();i++){
			ItemSet itemSet =(ItemSet)itemList.get(i);
			int saleLineId = i+1;
			SaleLine saleLine = createSaleLine
					(sale, saleLineId, itemSet, currentTime);
			sale.addSaleLine(saleLine);
		}
		return sale;
	}
	/*��¥���� Ŭ���� 
	 * java.sql,Date :��¥�� ���� Ŭ���� 
	 * java.sql.Time: �ð��� ����
	 * java.util.Date : ��¥ +�ð� Ŭ���� 
	 * java.sql.TimeStamp : ��¥ + �ð�Ŭ���� 
	 * 
	 * java.util.Calender : ��¥ + �ð� Ŭ���� 
	 *                           �������� ���
	 */
    private Timestamp getCurrentTime() {
		
		return new Timestamp(Calendar.getInstance().getTimeInMillis());
				//(System.currentTimeMillis());
	}

	//���� DB�� ����� (�ִ�saleId)+1 �� ���� ����
	private Integer getMaxSaleId() {
		return saleCatalog.getMaxSaleId();
	}

	private SaleLine createSaleLine
	                   (Sale sale, int saleLineId, ItemSet itemSet, Timestamp currentTime) {
		//SaleLine ������ �ϼ��ϱ� 
		return new SaleLine(sale,saleLineId,itemSet,currentTime);
	}

	private void insertSale(Sale sale) {
		saleCatalog.createSale(sale);
		
	}

	@Override
	public Integer calculateTotalAmount2(List<SaleLine> saleList) {
		int total =0;
		for(SaleLine is : saleList){
			total += 
					is.getItem().getPrice() * is.getQuantity();
		}
		return total;
	}

	@Override
	public int boardCount() {
		
		return boardCatalog.boardCount();
	}

	@Override
	public List<Board> getBoardList(Integer pageNum, int limit) {
		
		return boardCatalog.getboardlist(pageNum , limit);
	}

	@Override
	public Board selectOne(Integer num,Integer pageNum) {
		
		return boardCatalog.selectOne(num,pageNum);
	}

	@Override
	public void boardWrite(Board board, HttpServletRequest request) {
		if(board.getFile1() != null && !board.getFile1().isEmpty()){
			upload(board.getFile1(),request);
		
		}
		board.setFileUrl(board.getFile1().getOriginalFilename());
		boardCatalog.boardWrite(board,request);
	}

	@Override
	public void upload(MultipartFile upload, HttpServletRequest request) {
		String uploadPath = request.getServletContext().getRealPath("/")+"/upload/";
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(uploadPath + upload.getOriginalFilename());
			//picture.getInputStream():������ ������ �б����� ��Ʈ��
			InputStream in = upload.getInputStream();
			int data;
			while ((data = in.read()) != -1) {
				fos.write(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fos !=null){
				fos.flush();
				fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		
	}

	@Override
	public void rewrite(Board board) {
		 boardCatalog.rewrite(board);
	}

	@Override
	public void delete(Board board) {
		boardCatalog.delete(board);
		
	}

	@Override
	public void update(Board board , HttpServletRequest request) {
		if(board.getFile1() != null && !board.getFile1().isEmpty()){
			upload(board.getFile1(),request);
			board.setFileUrl(board.getFile1().getOriginalFilename());
		}else{
			board.setFileUrl(request.getParameter("file2"));
		}
		
		boardCatalog.update(board);
		
	}


	@Override
	public List<Board> search(String searchtype, String searchContent, Integer pageNum) {
		return boardCatalog.search(searchtype,searchContent,pageNum);
	}

	@Override
	public Member getUserByIdAndPw(String id, String pass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createUser(Member user) {
		// TODO Auto-generated method stub
		
	}

	


}

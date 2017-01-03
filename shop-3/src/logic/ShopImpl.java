package logic;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//@component + 서비스기능
@Service
public class ShopImpl implements Shop{
    @Autowired
	private ItemCatalog itemCatalog;
    @Autowired
    private UserCatalog userCatalog;
    @Autowired
    private SaleCatalog saleCatalog;
	@Override
	public Item getItemById(Integer itemId) {
		
		return itemCatalog.getItemById(itemId);
	}

	@Override
	public Cart getCart() {
		
		return new Cart();
	}

	@Override
	public User getUserByIdAndPw(String userId, String password) {
		
		return userCatalog.getUserByIdAndPw(userId,password);
	}

	@Override
	public void createUser(User user) {
		 userCatalog.createUser(user);
		
	}

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
	public Sale checkEnd(User loginUser, Cart cart) {
		//loginUser와cart 객체의 데이터를 가지고 sale객체 완성
		Sale sale = createSale(loginUser , cart);
		insertSale(sale);
		return sale;
	}

	private Sale createSale(User loginUser, Cart cart) {
		Sale sale = new Sale();
		sale.setSaleId(getMaxSaleId());
		sale.setUser(loginUser);
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
	/*날짜관련 클래스 
	 * java.sql,Date :날짜만 저장 클래스 
	 * java.sql.Time: 시간만 저장
	 * java.util.Date : 날짜 +시간 클래스 
	 * java.sql.TimeStamp : 날짜 + 시간클래스 
	 * 
	 * java.util.Calender : 날짜 + 시간 클래스 
	 *                           정보제공 기능
	 */
    private Timestamp getCurrentTime() {
		
		return new Timestamp(Calendar.getInstance().getTimeInMillis());
				//(System.currentTimeMillis());
	}

	//현재 DB에 저장된 (최대saleId)+1 의 값을 리턴
	private Integer getMaxSaleId() {
		return saleCatalog.getMaxSaleId();
	}

	private SaleLine createSaleLine
	                   (Sale sale, int saleLineId, ItemSet itemSet, Timestamp currentTime) {
		//SaleLine 생성자 완성하기 
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

	

}

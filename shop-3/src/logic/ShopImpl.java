package logic;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//@component + ���񽺱��
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
		//loginUser��cart ��ü�� �����͸� ������ sale��ü �ϼ�
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

	

}

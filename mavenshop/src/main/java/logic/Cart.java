package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

public class Cart implements Serializable {
	//itemList : īƮ�� ��ǰ���
	private List<ItemSet> itemList = new ArrayList<ItemSet>();
	public List<ItemSet> getItemList() {
		return itemList;

	}
	//īƮ�� ������ ��ǰ�� �����ϱ�
    //itmeSet :�߰� �Ǵ� ��ǰ
	public void push(ItemSet itemSet) {
//		if(!itemList.contains(itemSet.getItem().getItemId())){
//		itemList.add(itemSet);
//		}else{
//			ItemSet is =null;
//		is.setQuantity(is.getQuantity()+itemSet.getQuantity());
//		}
		Iterator<ItemSet> iter =itemList.iterator();
		ItemSet is =null;
		while(iter.hasNext()){
			is = iter.next();
			if(is.getItem().getItemId()== itemSet.getItem().getItemId()){
				is.setQuantity(is.getQuantity()+itemSet.getQuantity());
				return ;
			}
		}
		itemList.add(itemSet);
	}
	public String remove(int index) {
		String name = itemList.get(index).getItem().getItemName();
		itemList.remove(index);
		return name;
	}
	//�� īƮ���� ���θ� �˷��ش� .
	public boolean isEmpty() {
		if(itemList ==null || itemList.isEmpty()){
			return true;
		}
		return false;
	}
	public void clearAll(HttpSession session) {
		itemList = new ArrayList<ItemSet>();
		session.setAttribute("CART_KEY", this);
		
	}
	
}

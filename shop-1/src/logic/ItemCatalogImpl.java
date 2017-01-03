package logic;

import java.util.List;

import dao.ItemDao;

public class ItemCatalogImpl implements ItemCatalog{
private ItemDao itemDao;
public void setItemDao(ItemDao itemDao) {
	this.itemDao = itemDao;
}
	@Override
	public List<Item> getItemList() {
		
		return itemDao.findAll();
	}
	@Override
	public Item getItemByItemId(Integer itemId) {
		return itemDao.findById(itemId);
	}

}

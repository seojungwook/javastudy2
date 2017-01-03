package logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dao.ItemDao;

@Service
public class ItemCatalogImpl implements ItemCatalog{
    @Autowired
    private ItemDao itemDao;
	@Override
	public Item getItemById(Integer itemId) {
		return itemDao.getItemById(itemId);
	}

}

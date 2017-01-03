package logic;

import java.util.List;

public interface Shop {
	List<Item> getItemList();

	Item getItemByItemId(Integer itemId);
}

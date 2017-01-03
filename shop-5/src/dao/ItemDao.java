package dao;

import java.util.List;

import logic.Item;

public interface ItemDao {
    List<Item> getItemList();

	void addItem(Item item);
}

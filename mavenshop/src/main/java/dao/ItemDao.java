package dao;

import java.util.List;
import logic.Item;

public interface ItemDao {
List<Item> findAll();

Item getItemById(Integer itemId);

List<Item> searchList(String itemName);

void create(Item item);

void update(Item item);

void delete(Integer itemId);




}

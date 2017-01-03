package dao;

import java.util.List;
import logic.Item;

public interface ItemDao {
  List<Item> findAll();
Item findById(Integer itemId);
}

package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface ItemService {
List<Item> getItemList();

Item getItemById(Integer itemId);

List<Item> searchList(String itemName);

void entryItem(Item item, HttpServletRequest request);

void entryUpdate(Item item, HttpServletRequest request);

void delete(Integer itemId);


}

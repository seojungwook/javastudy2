package logic;

import java.util.List;

import logic.Cart;

public interface Shop {

	Item getItemById(Integer itemId);

	Cart getCart();

	User getUserByIdAndPw(String userId, String password);

	void createUser(User user);

	Integer calculateTotalAmount(List<ItemSet> itemList);

	Sale checkEnd(User loginUser, Cart cart);

	Integer calculateTotalAmount2(List<SaleLine> itemList);

}

package logic;

import java.io.Serializable;

public class ItemSet implements Serializable {
	private Item item;
	private Integer quantity;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ItemSet(Item item, Integer quantity) {
		this.item = item;
		this.quantity = quantity;

	}

	public Item getItem() {
		return item;

	}
}

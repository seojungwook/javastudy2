package logic;

import java.io.Serializable;
import java.sql.Timestamp;

public class SaleLine implements Serializable{
 private Sale sale;
 private Integer saleLineId;
 private Item item;
 private Timestamp updateTime;
 private Integer quantity;
 
public SaleLine
       (Sale sale, int saleLineId, ItemSet itemSet, Timestamp currentTime) {
	this.sale=sale;
	this.saleLineId =saleLineId;
	this.item=itemSet.getItem();
	this.updateTime = currentTime;
	this.quantity = itemSet.getQuantity();
}
public Sale getSale() {
	return sale;
}
public void setSale(Sale sale) {
	this.sale = sale;
}
public Integer getSaleLineId() {
	return saleLineId;
}
public void setSaleLineId(Integer saleLineId) {
	this.saleLineId = saleLineId;
}
public Item getItem() {
	return item;
}
public void setItem(Item item) {
	this.item = item;
}
public Timestamp getUpdateTime() {
	return updateTime;
}
public void setUpdateTime(Timestamp updateTime) {
	this.updateTime = updateTime;
}
public Integer getQuantity() {
	return quantity;
}
public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}
 
}

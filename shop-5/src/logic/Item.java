package logic;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;


public class Item implements Serializable{
 private Integer itemId;
 @NotEmpty //null 도아니고 공란도 아니고 
 private String itemName;
 @NotNull//null안됨
 @Min(0)
 @Max(500000)
 private Integer price;
 @NotEmpty
 private String description;
 private String pictureUrl;//이미지명
 //MultipartFile : 스프링에서 파일업로드 관련 클래스 
 //setPicture(MultipartFile picture){....}를 통해서 파일의 내용을 저장하는 변수
 private MultipartFile picture;

 
 public Integer getItemId() {
	return itemId;
}
public void setItemId(Integer itemId) {
	this.itemId = itemId;
}
public String getItemName() {
	return itemName;
}
public void setItemName(String itemName) {
	this.itemName = itemName;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Integer getPrice() {
	return price;
}
public void setPrice(Integer price) {
	this.price = price;
}
public String getPictureUrl() {
	return pictureUrl;
}
public void setPictureUrl(String pictureUrl) {
	this.pictureUrl = pictureUrl;
}
public MultipartFile getPicture() {
	return picture;
}
public void setPicture(MultipartFile picture) {
	this.picture = picture;
}
@Override
public String toString() {
	return "Item [itemId=" + itemId + ", itemName=" + itemName + ", description=" + description + ", price=" + price
			+ ", pictureUrl=" + pictureUrl + ", picture=" + picture + "]";
}
 
}

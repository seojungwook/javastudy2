package logic;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Sale implements Serializable{
 private Integer saleId;
 private Member member;
 private Timestamp updateTime;
 private List<SaleLine> saleLine = new ArrayList<SaleLine>();
public Integer getSaleId() {
	return saleId;
}
public void setSaleId(Integer saleId) {
	this.saleId = saleId;
}
public Member getMember() {
	return member;
}
public void setMember(Member loginMember) {
	this.member = loginMember;
}
public Timestamp getUpdateTime() {
	return updateTime;
}
public void setUpdateTime(Timestamp updateTime) {
	this.updateTime = updateTime;
}
public List<SaleLine> getSaleLine() {
	return saleLine;
}
public void setSaleLine(List<SaleLine> saleLine) {
	this.saleLine = saleLine;
}
//saleLine °´Ã¼¸¦ saleLineList List °´Ã¼ ¿¡ Ãß°¡ 
public void addSaleLine(SaleLine saleLine) {
	this.saleLine.add(saleLine);
	
}
 
}

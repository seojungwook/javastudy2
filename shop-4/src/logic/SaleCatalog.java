package logic;

public interface SaleCatalog {
	//상수 맴버로 허용
	//public final static int i = 100 
	//int i =100; //상수
	//public abstract 제어자 가능
	 void createSale(Sale sale);
	
	 Integer getMaxSaleId();
	
	
}

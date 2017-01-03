package dao;

import java.util.List;

import logic.Sale;

public interface SaleDao {

	void create(Sale sale);

	int findMaxSaleId();

	List<Sale> list(String id);

}

package dao;

import logic.Sale;

public interface SaleDao {

	void create(Sale sale);

	int findMaxSaleId();

}

package dao;

import java.util.List;

import logic.SaleLine;

public interface SaleLineDao {

	void create(SaleLine saleLine);

	List<SaleLine> list(Integer saleId);

}

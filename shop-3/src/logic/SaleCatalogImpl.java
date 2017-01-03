package logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.SaleDao;
import dao.SaleLineDao;

@Service
public class SaleCatalogImpl implements SaleCatalog{
    @Autowired
    private SaleDao saleDao;
    @Autowired
    private SaleLineDao saleLineDao;
	
    @Override
	public void createSale(Sale sale) {
	 saleDao.create(sale);
	 List<SaleLine> saleLineList = sale.getSaleLine();
	 for(SaleLine saleLine : saleLineList){
		 saleLineDao.create(saleLine);
	 }
	}

	@Override
	public Integer getMaxSaleId() {
	  	
		return saleDao.findMaxSaleId()+1;
	}

}

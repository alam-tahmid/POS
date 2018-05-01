package com.pos.lotto.service;

import java.util.List;

import com.pos.lotto.model.Sales;

public interface SalesService {

	public void saveSale(Sales sale);
	
	public List<Sales> findByInvoiceNo(String invoiceNo);
	
	public List<Sales> findAll();
}

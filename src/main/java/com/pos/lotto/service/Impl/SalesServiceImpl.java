package com.pos.lotto.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.lotto.model.Sales;
import com.pos.lotto.repository.SalesRepository;
import com.pos.lotto.service.SalesService;

@Service("salesService")
public class SalesServiceImpl implements SalesService {

	@Autowired
	private SalesRepository salesRepository;

	@Override
	public void saveSale(Sales sale) {
		salesRepository.save(sale);

	}

	@Override
	public List<Sales> findByInvoiceNo(String invoiceNo) {

		return salesRepository.findByInvoiceNo(invoiceNo);
	}

	@Override
	public List<Sales> findAll() {
		
		return salesRepository.findAll();
	}

	@Override
	public List<Sales> findDaySale(Date date) {
		
		return salesRepository.findDaysSale(date);
	}

}

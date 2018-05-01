package com.pos.lotto.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.lotto.model.InvoiceNumber;
import com.pos.lotto.repository.InvoiceRepository;
import com.pos.lotto.service.InvoiceService;

@Service("invoiceService")
public class InvoiceServiceImpl implements InvoiceService{

	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Override
	public InvoiceNumber findLast() {

		List<InvoiceNumber> invoices = invoiceRepository.findAll();
		InvoiceNumber invoiceNumber = invoices.get(invoices.size()-1);
		
		return invoiceNumber;
	}

	@Override
	public void saveInvoice(InvoiceNumber inv) {

		invoiceRepository.save(inv);
	}

}

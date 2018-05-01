package com.pos.lotto.service;

import com.pos.lotto.model.InvoiceNumber;

public interface InvoiceService {

	public void saveInvoice(InvoiceNumber inv);
	
	public InvoiceNumber findLast();
}

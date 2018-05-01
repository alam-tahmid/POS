package com.pos.lotto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pos.lotto.model.Sales;

@Repository("salesRepository")
public interface SalesRepository extends JpaRepository<Sales, Integer>{

	
	public List<Sales> findByInvoiceNo(String invoiceNo);
}

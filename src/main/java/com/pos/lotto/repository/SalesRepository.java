package com.pos.lotto.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pos.lotto.model.Sales;

@Repository("salesRepository")
public interface SalesRepository extends JpaRepository<Sales, Integer>{

	
	public List<Sales> findByInvoiceNo(String invoiceNo);
	
	@Query("Select s from Sales s where s.date=?1")
	public List<Sales> findDaysSale(Date date);
}

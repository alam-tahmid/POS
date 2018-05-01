package com.pos.lotto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pos.lotto.model.InvoiceNumber;

@Repository("invoiceRepository")
public interface InvoiceRepository extends JpaRepository<InvoiceNumber, Integer>{

}

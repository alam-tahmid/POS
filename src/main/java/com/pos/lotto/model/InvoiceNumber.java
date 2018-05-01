package com.pos.lotto.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "invoice_number")
public class InvoiceNumber {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "invoice_id")
	private int id;

	@Column(name = "invoice")
	private int invoice;

	@Column(name = "generated_invoice")
	private String generatedInvoice;

	@Temporal(TemporalType.DATE)
	@Column(name = "generated_on")
	private Date generatedOn;

	@Column(name = "generated_by")
	private String generateBy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInvoice() {
		return invoice;
	}

	public void setInvoice(int invoice) {
		this.invoice = invoice;
	}

	public String getGeneratedInvoice() {
		return generatedInvoice;
	}

	public void setGeneratedInvoice(String generatedInvoice) {
		this.generatedInvoice = generatedInvoice;
	}

	public Date getGeneratedOn() {
		return generatedOn;
	}

	public void setGeneratedOn(Date generatedOn) {
		this.generatedOn = generatedOn;
	}

	public String getGenerateBy() {
		return generateBy;
	}

	public void setGenerateBy(String generateBy) {
		this.generateBy = generateBy;
	}

}

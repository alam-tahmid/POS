package com.pos.lotto.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "expense")
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "expense_id")
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name = "expense_date")
	private Date date;

	@OneToMany(mappedBy = "expense", cascade = CascadeType.ALL, orphanRemoval = true)
	List<ChargeHead> chargeHeads = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<ChargeHead> getChargeHeads() {
		return chargeHeads;
	}

	public void setChargeHeads(List<ChargeHead> chargeHeads) {
		this.chargeHeads = chargeHeads;
	}

	public void addChargeHead(ChargeHead chargeHead) {
		chargeHeads.add(chargeHead);
		chargeHead.setExpense(this);
	}

	public void removeChargeHead(ChargeHead chargeHead) {
		chargeHeads.remove(chargeHead);
		chargeHead.setExpense(null);
	}

}

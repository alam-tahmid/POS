package com.pos.lotto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "charge_head")
public class ChargeHead {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "charge_head_id")
	private Long chargeHeadId;

	@Column(name = "charge_head")
	private String chargeHead;

	@Column(name = "condition_value")
	private Double conditionValue;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "expense_id")
	private Expense expense;

	public Long getChargeHeadId() {
		return chargeHeadId;
	}

	public String getChargeHead() {
		return chargeHead;
	}

	public void setChargeHead(String chargeHead) {
		this.chargeHead = chargeHead;
	}

	public Double getConditionValue() {
		return conditionValue;
	}

	public void setConditionValue(Double conditionValue) {
		this.conditionValue = conditionValue;
	}

	public Expense getExpnse() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (!(o instanceof ChargeHead)) {
			return false;
		}

		return chargeHeadId != null && chargeHeadId.equals(((ChargeHead) o).chargeHeadId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (chargeHeadId ^ (chargeHeadId >>> 32));
		return result;
	}
}

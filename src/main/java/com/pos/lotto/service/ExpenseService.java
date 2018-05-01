package com.pos.lotto.service;

import java.util.List;

import com.pos.lotto.model.Expense;

public interface ExpenseService {

	public void save(Expense expense);

	public List<Expense> findAll();

	public Expense getExpenseDetails(Integer id);
}

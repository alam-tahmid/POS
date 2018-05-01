package com.pos.lotto.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.lotto.model.Expense;
import com.pos.lotto.repository.ExpenseRepository;
import com.pos.lotto.service.ExpenseService;

@Service("expenseService")
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;

	@Override
	public List<Expense> findAll() {

		return expenseRepository.findAll();
	}

	@Override
	public Expense getExpenseDetails(Integer id) {

		return expenseRepository.findOne(id);
	}

	@Override
	public void save(Expense expense) {

		expenseRepository.save(expense);

	}

}

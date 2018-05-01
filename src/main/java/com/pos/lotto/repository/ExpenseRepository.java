package com.pos.lotto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pos.lotto.model.Expense;

@Repository("expenseRepository")
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

}

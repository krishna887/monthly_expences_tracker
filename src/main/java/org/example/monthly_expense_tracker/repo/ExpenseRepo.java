package org.example.monthly_expense_tracker.repo;

import org.example.monthly_expense_tracker.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepo extends JpaRepository<Expenses,Integer> {
}

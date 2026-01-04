package org.example.monthly_expense_tracker.service;

import lombok.RequiredArgsConstructor;
import org.example.monthly_expense_tracker.repo.ExpenseRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseService {
  private final ExpenseRepo expenseRepo;


  public double getTotalForDay(LocalDate date) {
    return Optional.ofNullable(
            expenseRepo.findDailyTotal(date)
    ).orElse(0.0);
  }

  public double getTotalForMonth(int year, int month) {
    LocalDate start = LocalDate.of(year, month, 1);
    LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

    return Optional.ofNullable(
            expenseRepo.findMonthlyTotal(start, end)
    ).orElse(0.0);
  }

}

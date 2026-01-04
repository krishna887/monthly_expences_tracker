package org.example.monthly_expense_tracker.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ExpenseServiceTest {
  @Autowired
  private ExpenseService expenseService;
  @Test
  void shouldReturnTotalOfADay(){
    LocalDate date=LocalDate.of(2026,1,1);
    double totalForDay = expenseService.getTotalForDay(date);
    System.out.println(totalForDay);
  }
@Test
  void shouodReturnMonthlyTotal(){
  System.out.println(expenseService.getTotalForMonth(2026,1));
}

}
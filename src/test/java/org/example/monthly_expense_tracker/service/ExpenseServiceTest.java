package org.example.monthly_expense_tracker.service;

import org.example.monthly_expense_tracker.repo.ExpenseRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {
  @Mock
  ExpenseRepo expenseRepo;
  @InjectMocks
  ExpenseService expenseService;

  @Test
  void getTotalForDay_shouldReturnTotal_whenExpensesExist() {
    // Arrange
    LocalDate today = LocalDate.of(2024, 1, 15);
    when(expenseRepo.findDailyTotal(today)).thenReturn(250.0);

    // Act
    double result = expenseService.getTotalForDay(today);

    // Assert
    assertThat(result).isEqualTo(250.0);
  }

  @Test
  void getTotalForDay_shouldReturnZero_whenNoExpensesExist() {
    // Arrange
    LocalDate today = LocalDate.of(2024, 1, 15);
    when(expenseRepo.findDailyTotal(today)).thenReturn(null); // repo returns null

    // Act
    double result = expenseService.getTotalForDay(today);

    // Assert
    assertThat(result).isEqualTo(0.0);  // your Optional.orElse(0.0) handles this
  }
  @Test
  void getTotalForMonth_shouldCalculateCorrectDateRange() {
    // Arrange
    LocalDate start = LocalDate.of(2024, 1, 1);
    LocalDate end = LocalDate.of(2024, 1, 31);
    when(expenseRepo.findMonthlyTotal(start, end)).thenReturn(1500.0);

    // Act
    double result = expenseService.getTotalForMonth(2024, 1);

    // Assert
    assertThat(result).isEqualTo(1500.0);
  }

  @Test
  void getTotalForMonth_shouldReturnZero_whenNull() {
    LocalDate start = LocalDate.of(2024, 2, 1);
    LocalDate end = LocalDate.of(2024, 2, 29); // 2024 is leap year
    when(expenseRepo.findMonthlyTotal(start, end)).thenReturn(null);

    double result = expenseService.getTotalForMonth(2024, 2);

    assertThat(result).isEqualTo(0.0);
  }

}
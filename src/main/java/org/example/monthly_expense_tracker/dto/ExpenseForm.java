package org.example.monthly_expense_tracker.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ExpenseForm {
  private LocalDate date;
  private List<ExpenseItemDto> items = new ArrayList<>();
}

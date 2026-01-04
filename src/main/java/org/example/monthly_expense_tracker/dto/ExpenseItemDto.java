package org.example.monthly_expense_tracker.dto;

import lombok.Data;

@Data
public class ExpenseItemDto {
  private String name;
  private double price;
}

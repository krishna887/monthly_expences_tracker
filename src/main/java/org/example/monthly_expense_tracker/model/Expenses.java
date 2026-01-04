package org.example.monthly_expense_tracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Expenses {
  @Id
  @GeneratedValue(strategy =  GenerationType.IDENTITY)
  private Integer id;
  private String title;
  private LocalDate date;
  private double pricePerTitle;
}

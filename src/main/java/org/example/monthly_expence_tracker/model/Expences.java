package org.example.monthly_expence_tracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Expences {
  @Id
  @GeneratedValue(strategy =  GenerationType.IDENTITY)
  private int id;
  private String title;
  private Date date;
  private double pricePerTitle;
  private double totalPrice;
}

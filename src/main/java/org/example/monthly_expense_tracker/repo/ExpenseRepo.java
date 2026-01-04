package org.example.monthly_expense_tracker.repo;

import org.example.monthly_expense_tracker.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface ExpenseRepo extends JpaRepository<Expenses,Integer> {
  @Query("SELECT SUM(e.pricePerTitle) FROM Expenses e WHERE e.date = :date")
  Double findDailyTotal(@Param("date") LocalDate date);

  @Query("""
        SELECT SUM(e.pricePerTitle)
        FROM Expenses e
        WHERE e.date BETWEEN :start AND :end
    """)
  Double findMonthlyTotal(
          @Param("start") LocalDate start,
          @Param("end") LocalDate end
  );

}

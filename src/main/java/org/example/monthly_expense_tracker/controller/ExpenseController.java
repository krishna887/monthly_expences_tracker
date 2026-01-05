package org.example.monthly_expense_tracker.controller;

import lombok.RequiredArgsConstructor;
import org.example.monthly_expense_tracker.dto.ExpenseForm;
import org.example.monthly_expense_tracker.dto.ExpenseItemDto;
import org.example.monthly_expense_tracker.model.Expenses;
import org.example.monthly_expense_tracker.repo.ExpenseRepo;
import org.example.monthly_expense_tracker.service.ExpenseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseController {

  private final ExpenseService expenseService;
  private final ExpenseRepo expenseRepository;

  @GetMapping("/add")
  public String showForm(Model model) {
    ExpenseForm form = new ExpenseForm();
    form.getItems().add(new ExpenseItemDto()); // start with 1 row

    model.addAttribute("expenseForm", form);
    model.addAttribute("dailyTotal", 0.0);
    model.addAttribute("monthlyTotal", 0.0);
    return "expense-form";
  }

  @PostMapping("/add")
  public String submitForm(@ModelAttribute ExpenseForm expenseForm, Model model) {
    List<ExpenseItemDto> validItems = expenseForm.getItems().stream()
            .filter(item -> item.getName() != null && !item.getName().trim().isEmpty()
                    && item.getPrice() > 0)
            .toList();

    for (ExpenseItemDto item : validItems) {
      Expenses expense = new Expenses();
      expense.setTitle(item.getName());
      expense.setPricePerTitle(item.getPrice());
      expense.setDate(expenseForm.getDate());
      expenseRepository.save(expense);
    }

    // get totals
    double dailyTotal = expenseService.getTotalForDay(expenseForm.getDate());
    double monthlyTotal = expenseService.getTotalForMonth(
            expenseForm.getDate().getYear(),
            expenseForm.getDate().getMonthValue()
    );

    ExpenseForm form = new ExpenseForm();
    form.setDate(expenseForm.getDate()); // keep selected date
    form.getItems().add(new ExpenseItemDto()); // 1 empty row


    model.addAttribute("expenseForm", form);
    model.addAttribute("dailyTotal", dailyTotal);
    model.addAttribute("monthlyTotal", monthlyTotal);
    return "expense-form";
  }

}


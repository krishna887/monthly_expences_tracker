package org.example.monthly_expense_tracker.controller;

import org.example.monthly_expense_tracker.repo.ExpenseRepo;
import org.example.monthly_expense_tracker.service.ExpenseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExpenseController.class)
class ExpenseControllerTest {
  @Autowired
  MockMvc mockMvc;
  @MockitoBean
  ExpenseRepo expenseRepo;
  @MockitoBean
  ExpenseService expenseService;
  @Test
  @WithMockUser(username = "testuser", roles = "USER")  // simulates logged in user
  void showForm_shouldReturnExpenseFormView() throws Exception {
    mockMvc.perform(get("/expenses/add"))
            .andExpect(status().isOk())
            .andExpect(view().name("expense-form"))        // checks template name
            .andExpect(model().attributeExists("expenseForm")); // checks model data
  }


}
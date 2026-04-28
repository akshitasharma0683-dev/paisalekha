package com.akshita.paisalekha.service;
import java.util.List;

import com.akshita.paisalekha.Entity.*;

public interface ExpenseService {

    Expense createExpense(Expense expense, User user, Category category);

    List<Expense> getUserExpenses(User user);
}

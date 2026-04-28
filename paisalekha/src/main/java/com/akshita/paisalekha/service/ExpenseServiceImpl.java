package com.akshita.paisalekha.service;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshita.paisalekha.Entity.*;
import com.akshita.paisalekha.Repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Expense createExpense(Expense expense, User user, Category category) {

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (category == null) {
            throw new RuntimeException("Category not found");
        }

        expense.setUser(user);
        expense.setCategory(category);

        // ✅ required fields
        if (expense.getExpenseDate() == null) {
            expense.setExpenseDate(LocalDate.now());
        }

        if (expense.getPaymentMethod() == null || expense.getPaymentMethod().isEmpty()) {
            expense.setPaymentMethod("CASH");
        }

        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getUserExpenses(User user) {
        return expenseRepository.findByUser(user);
    }
}

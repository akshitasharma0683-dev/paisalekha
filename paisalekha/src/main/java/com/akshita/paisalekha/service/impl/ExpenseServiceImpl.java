package com.akshita.paisalekha.service.impl;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshita.paisalekha.Entity.*;
import com.akshita.paisalekha.Repository.ExpenseRepository;
import com.akshita.paisalekha.service.ExpenseService;

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

    @Override
    public Expense updateExpense(Long expenseId,
                                 Expense updatedExpense,
                                 User user,
                                 Category category) {

        Expense existingExpense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        // ownership check
        if (!existingExpense.getUser().getUserId()
                .equals(user.getUserId())) {

            throw new RuntimeException("Unauthorized expense update");
        }

        // category validation
        if (category == null) {
            throw new RuntimeException("Category not found");
        }

        if (updatedExpense.getAmount() != null) {
            existingExpense.setAmount(updatedExpense.getAmount());
        }

        if (updatedExpense.getDescription() != null) {
            existingExpense.setDescription(updatedExpense.getDescription());
        }

        if (updatedExpense.getPaymentMethod() != null
                && !updatedExpense.getPaymentMethod().isEmpty()) {

            existingExpense.setPaymentMethod(updatedExpense.getPaymentMethod());
        }

        if (updatedExpense.getExpenseDate() != null) {
            existingExpense.setExpenseDate(updatedExpense.getExpenseDate());
        }
        existingExpense.setCategory(category);

        return expenseRepository.save(existingExpense);
    }

    @Override
    public void deleteExpense(Long expenseId, User user) {

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        // ownership check
        if (!expense.getUser().getUserId()
                .equals(user.getUserId())) {

            throw new RuntimeException("Unauthorized expense deletion");
        }

        expenseRepository.delete(expense);
    }

    @Override
    public Double getWeeklyExpense(User user) {

        LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);

        return expenseRepository.findByUser(user)
                .stream()
                .filter(expense ->
                        expense.getExpenseDate() != null &&
                        !expense.getExpenseDate().isBefore(sevenDaysAgo))
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    @Override
    public Double getMonthlyExpense(User user) {

        LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);

        return expenseRepository.findByUser(user)
                .stream()
                .filter(expense ->
                        expense.getExpenseDate() != null &&
                        !expense.getExpenseDate().isBefore(thirtyDaysAgo))
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}

package com.akshita.paisalekha.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.akshita.paisalekha.Entity.Category;
import com.akshita.paisalekha.Entity.Expense;
import com.akshita.paisalekha.Entity.User;
import com.akshita.paisalekha.Repository.CategoryRepository;
import com.akshita.paisalekha.service.ExpenseService;
import com.akshita.paisalekha.service.UserService;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryRepository categoryRepository;

    // CREATE EXPENSE
    @PostMapping
    public ResponseEntity<Expense> createExpense(
            @RequestBody Expense expense,
            @RequestParam Long categoryId) {

    	String username = SecurityContextHolder
    	        .getContext()
    	        .getAuthentication()
    	        .getName();
    	
        User user = userService.findByUsername(username);

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Expense saved = expenseService.createExpense(expense, user, category);

        return ResponseEntity.ok(saved);
    }

    // GET USER EXPENSES
    @GetMapping
    public ResponseEntity<List<Expense>> getExpenses() {

        String username =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User user = userService.findByUsername(username);

        return ResponseEntity.ok(
                expenseService.getUserExpenses(user)
        );
    }

    // UPDATE EXPENSE
    @PutMapping("/{expenseId}")
    public ResponseEntity<Expense> updateExpense(
            @PathVariable Long expenseId,
            @RequestBody Expense updatedExpense,
            @RequestParam String username,
            @RequestParam Long categoryId) {

        User user = userService.findByUsername(username);

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Expense expense = expenseService.updateExpense(
                expenseId,
                updatedExpense,
                user,
                category
        );

        return ResponseEntity.ok(expense);
    }

    // DELETE EXPENSE
    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> deleteExpense(
            @PathVariable Long expenseId,
            @RequestParam String username) {

        User user = userService.findByUsername(username);

        expenseService.deleteExpense(expenseId, user);

        return ResponseEntity.noContent().build();
    }

    // WEEKLY EXPENSE
    @GetMapping("/weekly")
    public ResponseEntity<Double> getWeeklyExpense(
            @RequestParam String username) {

        User user = userService.findByUsername(username);

        return ResponseEntity.ok(
                expenseService.getWeeklyExpense(user)
        );
    }

    // MONTHLY EXPENSE
    @GetMapping("/monthly")
    public ResponseEntity<Double> getMonthlyExpense(
            @RequestParam String username) {

        User user = userService.findByUsername(username);

        return ResponseEntity.ok(
                expenseService.getMonthlyExpense(user)
        );
    }
}
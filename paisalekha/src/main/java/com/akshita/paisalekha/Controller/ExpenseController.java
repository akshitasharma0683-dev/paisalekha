package com.akshita.paisalekha.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akshita.paisalekha.Entity.Category;
import com.akshita.paisalekha.Entity.Expense;
import com.akshita.paisalekha.Entity.User;
import com.akshita.paisalekha.Repository.CategoryRepository;
import com.akshita.paisalekha.service.ExpenseService;
import com.akshita.paisalekha.service.UserService;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

	
    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity<Expense> createExpense(
            @RequestBody Expense expense,
            @RequestParam String username,
            @RequestParam Long categoryId) {

        System.out.println("USERNAME PARAM: " + username);

        User user = userService.findByUsername(username);
        System.out.println("FOUND USER: " + user);

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        System.out.println("FOUND CATEGORY: " + category);

        Expense saved = expenseService.createExpense(expense, user, category);

        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getExpenses(
            @RequestParam String username) {

        User user = userService.findByUsername(username);

        return ResponseEntity.ok(
                expenseService.getUserExpenses(user)
        );
    }
}
package com.akshita.paisalekha.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.akshita.paisalekha.Entity.Income;
import com.akshita.paisalekha.Entity.User;
import com.akshita.paisalekha.service.IncomeService;
import com.akshita.paisalekha.service.UserService;

@RestController
@RequestMapping("/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private UserService userService;

    // CREATE INCOME
    @PostMapping
    public ResponseEntity<Income> createIncome(
            @RequestBody Income income,
            @RequestParam String username) {

        User user = userService.findByUsername(username);

        Income savedIncome = incomeService.createIncome(income, user);

        return ResponseEntity.ok(savedIncome);
    }

    // GET USER INCOME
    @GetMapping
    public ResponseEntity<List<Income>> getIncome(
            @RequestParam String username) {

        User user = userService.findByUsername(username);

        return ResponseEntity.ok(
                incomeService.getUserIncome(user)
        );
    }

    // UPDATE INCOME
    @PutMapping("/{incomeId}")
    public ResponseEntity<Income> updateIncome(
            @PathVariable Long incomeId,
            @RequestBody Income updatedIncome,
            @RequestParam String username) {

        User user = userService.findByUsername(username);

        Income income = incomeService.updateIncome(
                incomeId,
                updatedIncome,
                user
        );

        return ResponseEntity.ok(income);
    }

    // DELETE INCOME
    @DeleteMapping("/{incomeId}")
    public ResponseEntity<Void> deleteIncome(
            @PathVariable Long incomeId,
            @RequestParam String username) {

        User user = userService.findByUsername(username);

        incomeService.deleteIncome(incomeId, user);

        return ResponseEntity.noContent().build();
    }
}
package com.akshita.paisalekha.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.akshita.paisalekha.Entity.Income;
import com.akshita.paisalekha.Entity.User;
import com.akshita.paisalekha.service.IncomeService;
import com.akshita.paisalekha.service.UserService;

@RestController
@RequestMapping("/api/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private UserService userService;

    @PostMapping
    public Income createIncome(
            @RequestBody Income income,
            @RequestParam String username) {

        User user = userService.findByUsername(username);

        return incomeService.createIncome(income, user);
    }

    @GetMapping
    public List<Income> getIncome(@RequestParam String username) {

        User user = userService.findByUsername(username);

        return incomeService.getUserIncome(user);
    }
}
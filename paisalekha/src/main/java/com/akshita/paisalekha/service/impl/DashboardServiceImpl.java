package com.akshita.paisalekha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshita.paisalekha.Entity.Expense;
import com.akshita.paisalekha.Entity.User;
import com.akshita.paisalekha.Repository.CategoryRepository;
import com.akshita.paisalekha.Repository.ExpenseRepository;
import com.akshita.paisalekha.Repository.IncomeRepository;
import com.akshita.paisalekha.dto.DashboardResponse;

@Service
public class DashboardServiceImpl implements DashboardService {
	@Autowired
	private IncomeRepository incomeRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public DashboardResponse getDashboard(String username) {

        User user = userService.findByUsername(username);

        Double totalExpense = expenseRepository.getTotalExpenseByUser(user);
        if (totalExpense == null) totalExpense = 0.0;

        Double totalIncome = incomeRepository.getTotalIncomeByUser(user);
        if (totalIncome == null) totalIncome = 0.0;

        Double balance = totalIncome - totalExpense;
        
       
        
        Long expenseCount = expenseRepository.countByUser(user);
        Long totalCategories = categoryRepository.countByUser(user);

        List<Expense> recentExpenses =
                expenseRepository.findTop5ByUserOrderByExpenseDateDesc(user);

        DashboardResponse response = new DashboardResponse();
        response.setTotalExpense(totalExpense);
        response.setExpenseCount(expenseCount);
        response.setTotalCategories(totalCategories);
        response.setRecentExpenses(recentExpenses);
        response.setBalance(balance);


        return response;
    }
}
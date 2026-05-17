package com.akshita.paisalekha.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshita.paisalekha.Entity.Income;
import com.akshita.paisalekha.Entity.User;
import com.akshita.paisalekha.Repository.IncomeRepository;
import com.akshita.paisalekha.service.IncomeService;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Override
    public Income createIncome(Income income, User user) {

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        income.setUser(user);

        if (income.getIncomeDate() == null) {
            income.setIncomeDate(LocalDate.now());
        }

        return incomeRepository.save(income);
    }

    @Override
    public List<Income> getUserIncome(User user) {
        return incomeRepository.findByUser(user);
    }
    
    @Override
    public Income updateIncome(Long incomeId,
                               Income updatedIncome,
                               User user) {

        Income existingIncome = incomeRepository.findById(incomeId)
                .orElseThrow(() -> new RuntimeException("Income not found"));

        // ownership check
        if (!existingIncome.getUser().getUserId()
                .equals(user.getUserId())) {

            throw new RuntimeException("Unauthorized income update");
        }

        // amount validation
        if (updatedIncome.getAmount() != null
                && updatedIncome.getAmount() <= 0) {

            throw new RuntimeException("Income amount must be positive");
        }

        // safe partial updates
        if (updatedIncome.getAmount() != null) {
            existingIncome.setAmount(updatedIncome.getAmount());
        }

        if (updatedIncome.getSource() != null
                && !updatedIncome.getSource().isEmpty()) {

            existingIncome.setSource(updatedIncome.getSource());
        }

        if (updatedIncome.getIncomeDate() != null) {
            existingIncome.setIncomeDate(updatedIncome.getIncomeDate());
        }

        return incomeRepository.save(existingIncome);
    }
    
    @Override
    public void deleteIncome(Long incomeId, User user) {

        Income income = incomeRepository.findById(incomeId)
                .orElseThrow(() -> new RuntimeException("Income not found"));

        // ownership check
        if (!income.getUser().getUserId()
                .equals(user.getUserId())) {

            throw new RuntimeException("Unauthorized income deletion");
        }

        incomeRepository.delete(income);
    }
}
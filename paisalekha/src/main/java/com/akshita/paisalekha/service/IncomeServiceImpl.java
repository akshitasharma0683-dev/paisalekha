package com.akshita.paisalekha.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshita.paisalekha.Entity.Income;
import com.akshita.paisalekha.Entity.User;
import com.akshita.paisalekha.Repository.IncomeRepository;

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
}
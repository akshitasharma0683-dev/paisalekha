package com.akshita.paisalekha.service;

import java.util.List;

import com.akshita.paisalekha.Entity.Income;
import com.akshita.paisalekha.Entity.User;

public interface IncomeService {

    Income createIncome(Income income, User user);

    List<Income> getUserIncome(User user);
}
package com.akshita.paisalekha.dto;

import java.util.List;
import com.akshita.paisalekha.Entity.Expense;

public class DashboardResponse {

    private Double totalExpense;
    private Long totalCategories;
    private Long expenseCount;
    private List<Expense> recentExpenses;
    private Double totalIncome;
    private Double balance;
    
    
	public Double getTotalExpense() {
		return totalExpense;
	}
	public void setTotalExpense(Double totalExpense) {
		this.totalExpense = totalExpense;
	}
	public Long getTotalCategories() {
		return totalCategories;
	}
	public void setTotalCategories(Long totalCategories) {
		this.totalCategories = totalCategories;
	}
	public Long getExpenseCount() {
		return expenseCount;
	}
	public void setExpenseCount(Long expenseCount) {
		this.expenseCount = expenseCount;
	}
	public List<Expense> getRecentExpenses() {
		return recentExpenses;
	}
	public void setRecentExpenses(List<Expense> recentExpenses) {
		this.recentExpenses = recentExpenses;
	}
	public Double getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(Double totalIncome) {
		this.totalIncome = totalIncome;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}

    
}
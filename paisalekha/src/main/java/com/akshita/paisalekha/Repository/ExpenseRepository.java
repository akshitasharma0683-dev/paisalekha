package com.akshita.paisalekha.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.akshita.paisalekha.Entity.*;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUser(User user);

    List<Expense> findByUserAndCategory(User user, Category category);
    Long countByUser(User user);

    List<Expense> findTop5ByUserOrderByExpenseDateDesc(User user);
    
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.user = :user")
    Double getTotalExpenseByUser(User user);
}

package com.akshita.paisalekha.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.akshita.paisalekha.Entity.Income;
import com.akshita.paisalekha.Entity.User;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByUser(User user);

    Long countByUser(User user);
    
    @Query("SELECT SUM(i.amount) FROM Income i WHERE i.user = :user")
    Double getTotalIncomeByUser(User user);
}
package com.akshita.paisalekha.Repository;

import com.akshita.paisalekha.Entity.Category;
import com.akshita.paisalekha.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByUser(User user);

    Optional<Category> findByUserAndName(User user, String name);
    
    Long countByUser(User user);
}
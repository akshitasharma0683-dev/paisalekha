package com.akshita.paisalekha.service;

import com.akshita.paisalekha.Entity.Category;
import com.akshita.paisalekha.Entity.User;

import java.util.List;

public interface CategoryService {

        Category createCategory(Category category, User user);

        List<Category> getUserCategories(User user);

        Category updateCategory(Long categoryId, Category updatedCategory, User user);

        void deleteCategory(Long categoryId, User user);
        
        public void createDefaultCategories(User user);
    }

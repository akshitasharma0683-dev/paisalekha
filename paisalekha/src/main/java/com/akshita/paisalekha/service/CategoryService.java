package com.akshita.paisalekha.service;

import com.akshita.paisalekha.Entity.Category;
import com.akshita.paisalekha.Entity.User;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category, User user);

    List<Category> getUserCategories(User user);
}
package com.akshita.paisalekha.service;

import com.akshita.paisalekha.Entity.Category;
import com.akshita.paisalekha.Entity.User;
import com.akshita.paisalekha.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category, User user) {

        Optional<Category> existing =
                categoryRepository.findByUserAndName(user, category.getName());

        if (existing.isPresent()) {
            throw new RuntimeException("Category already exists for this user");
        }

        category.setUser(user);

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getUserCategories(User user) {
        return categoryRepository.findByUser(user);
    }
}
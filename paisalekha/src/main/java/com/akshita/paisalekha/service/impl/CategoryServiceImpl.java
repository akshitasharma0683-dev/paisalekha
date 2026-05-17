package com.akshita.paisalekha.service.impl;

import com.akshita.paisalekha.Entity.Category;
import com.akshita.paisalekha.Entity.User;
import com.akshita.paisalekha.Repository.CategoryRepository;
import com.akshita.paisalekha.service.CategoryService;

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

        List<Category> defaultCategories =
                categoryRepository.findByIsDefaultTrue();

        List<Category> customCategories =
                categoryRepository.findByUser(user);

        defaultCategories.addAll(customCategories);

        return defaultCategories;
    }

    @Override
    public Category updateCategory(Long categoryId,
                                   Category updatedCategory,
                                   User user) {

        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // prevent editing default categories
        if (Boolean.TRUE.equals(existingCategory.getIsDefault())) {
            throw new RuntimeException("Default categories cannot be updated");
        }

        // ownership check
        if (!existingCategory.getUser().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Unauthorized category update");
        }

        // duplicate name check
        Optional<Category> duplicate =
                categoryRepository.findByUserAndName(user, updatedCategory.getName());

        if (duplicate.isPresent()
                && !duplicate.get().getCategoryId().equals(categoryId)) {

            throw new RuntimeException("Category with this name already exists");
        }

        existingCategory.setName(updatedCategory.getName());
        existingCategory.setDescription(updatedCategory.getDescription());

        return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long categoryId, User user) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // prevent deleting default categories
        if (Boolean.TRUE.equals(category.getIsDefault())) {
            throw new RuntimeException("Default categories cannot be deleted");
        }

        // ownership check
        if (!category.getUser().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Unauthorized category deletion");
        }

        categoryRepository.delete(category);
    }
}
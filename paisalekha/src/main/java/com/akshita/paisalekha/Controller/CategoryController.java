package com.akshita.paisalekha.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akshita.paisalekha.Entity.Category;
import com.akshita.paisalekha.Entity.User;
import com.akshita.paisalekha.service.CategoryService;
import com.akshita.paisalekha.service.UserService;

@RestController
	@RequestMapping("/categories")
	public class CategoryController {

	    @Autowired
	    private CategoryService categoryService;

	    @Autowired
	    private UserService userService;

	    @PostMapping
	    public ResponseEntity<Category> createCategory(
	            @RequestBody Category category
	            ) {
	    	
	    	String username = SecurityContextHolder
	    	        .getContext()
	    	        .getAuthentication()
	    	        .getName();
	    	User user = userService.findByUsername(username);
	        return ResponseEntity.ok(
	                categoryService.createCategory(category, user)
	        );
	    }

	    @GetMapping
	    public ResponseEntity<List<Category>> getCategories(
	            ) {
	    	
	    	String username = SecurityContextHolder
	    	        .getContext()
	    	        .getAuthentication()
	    	        .getName();
	        User user = userService.findByUsername(username);

	        return ResponseEntity.ok(
	                categoryService.getUserCategories(user)
	        );
	    }
	    
	    @PutMapping("/{categoryId}")
	    public ResponseEntity<Category> updateCategory(
	            @PathVariable Long categoryId,
	            @RequestBody Category updatedCategory
	           ) {
	    	String username = SecurityContextHolder
	    	        .getContext()
	    	        .getAuthentication()
	    	        .getName();
	        User user = userService.findByUsername(username);

	        Category category = categoryService.updateCategory(
	                categoryId,
	                updatedCategory,
	                user
	        );

	        return ResponseEntity.ok(category);
	    }
	    
	    @DeleteMapping("/{categoryId}")
	    public ResponseEntity<Void> deleteCategory(
	            @PathVariable Long categoryId
	            ) {
	    	String username = SecurityContextHolder
	    	        .getContext()
	    	        .getAuthentication()
	    	        .getName();
	        User user = userService.findByUsername(username);

	        categoryService.deleteCategory(categoryId, user);

	        return ResponseEntity.noContent().build();
	    }
	}


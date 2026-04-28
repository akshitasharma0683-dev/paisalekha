package com.akshita.paisalekha.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akshita.paisalekha.Entity.Category;
import com.akshita.paisalekha.Entity.User;
import com.akshita.paisalekha.service.CategoryService;
import com.akshita.paisalekha.service.UserService;

@RestController
	@RequestMapping("/api/categories")
	public class CategoryController {

	    @Autowired
	    private CategoryService categoryService;

	    @Autowired
	    private UserService userService;

	    @PostMapping
	    public ResponseEntity<Category> createCategory(
	            @RequestBody Category category,
	            @RequestParam String username) {

	        User user = userService.getUserByUsername(username);

	        return ResponseEntity.ok(
	                categoryService.createCategory(category, user)
	        );
	    }

	    @GetMapping
	    public ResponseEntity<List<Category>> getCategories(
	            @RequestParam String username) {

	        User user = userService.getUserByUsername(username);

	        return ResponseEntity.ok(
	                categoryService.getUserCategories(user)
	        );
	    }
	}


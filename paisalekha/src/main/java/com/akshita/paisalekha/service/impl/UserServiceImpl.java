package com.akshita.paisalekha.service.impl;

import com.akshita.paisalekha.Entity.User;
import com.akshita.paisalekha.Repository.UserRepository;
import com.akshita.paisalekha.service.CategoryService;
import com.akshita.paisalekha.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
               
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    @Override
    public void deleteUser(Long userId) {

        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }

        userRepository.deleteById(userId);
    }
    
    public User registerUser(User user) {
        System.out.println(" we are in service :registerUser "+user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        if (user.getRole() == null) {
            user.setRole("ROLE_USER");
        }
        User savedUser = userRepository.save(user);

        categoryService.createDefaultCategories(savedUser);

        return savedUser;

    }
    
    public User loginUser(String username, String password) {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
package com.akshita.paisalekha.service;

import com.akshita.paisalekha.Entity.User;

public interface UserService {

    User registerUser(User user);

    User getUserByUsername(String username);

    User getUserById(Long userId);
    
    void deleteUser(Long userId);
}
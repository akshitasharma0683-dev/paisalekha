package com.akshita.paisalekha.Repository;

import com.akshita.paisalekha.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Used for login / authentication
    Optional<User> findByUsername(String username);

    // Used for registration validation
    Optional<User> findByEmail(String email);

    // Optional helper
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
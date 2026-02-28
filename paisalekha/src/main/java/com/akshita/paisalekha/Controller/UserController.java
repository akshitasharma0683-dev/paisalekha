package com.akshita.paisalekha.Controller;

import com.akshita.paisalekha.Entity.User;
import com.akshita.paisalekha.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        User savedUser = userService.registerUser(user);

        return ResponseEntity.ok(savedUser);
    }
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Long userId) {

	    userService.deleteUser(userId);

	    return ResponseEntity.ok("User deleted successfully");
	}
}

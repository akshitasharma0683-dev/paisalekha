package com.akshita.paisalekha.Controller;

import com.akshita.paisalekha.Entity.User;
import com.akshita.paisalekha.dto.LoginRequest;
import com.akshita.paisalekha.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
	 
	private final UserService userService;

	public UserController(UserService userService) {
	    this.userService = userService;
	}
    

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        System.out.println(" we are in controller :registerUser "+user);

        User savedUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Long userId) {

	    userService.deleteUser(userId);

	    return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {

	    try {
			User user = userService.loginUser(
			        request.getUsername(),
			        request.getPassword()
			);

			return ResponseEntity.ok(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

		}
	}
}

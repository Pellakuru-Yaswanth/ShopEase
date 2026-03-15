package com.shopease.controller;

import com.shopease.bean.identity.User;
import com.shopease.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Allows your frontend to connect
public class UserController {

    @Autowired
    private UserService userService;

    // 1. POST: Register a new user
    // Endpoint: http://localhost:8080/api/users/register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 2. POST: Login
    // Endpoint: http://localhost:8080/api/users/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");
        
        User user = userService.login(email, password);
        
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }

    // 3. GET: Get Profile Details
    // Endpoint: http://localhost:8080/api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getProfile(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
    }

    // 4. PUT: Update Profile
    // Endpoint: http://localhost:8080/api/users/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateProfile(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateProfile(id, userDetails);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
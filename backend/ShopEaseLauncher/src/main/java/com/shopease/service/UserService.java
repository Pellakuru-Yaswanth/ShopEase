package com.shopease.service;

import com.shopease.bean.identity.User;
import com.shopease.repository.identity.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 1. Register a new user
    public User registerUser(User user) throws Exception{
    	user.setUserId(null);
    	System.out.println(user.getEmail());
        if (userRepository.existsByEmail(user.getEmail())) {
        	System.out.println("Email already exists");
            throw new Exception("Email already exists");
        }
        // In a real app, you would encrypt the password here before saving
        return userRepository.save(user);
    }

    // 2. Login Logic
    public User login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Simple text check (Production should use BCrypt)
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // 3. Update Profile
    public User updateProfile(Long userId, User updatedDetails) {
        return userRepository.findById(userId).map(user -> {
            user.setFirstName(updatedDetails.getFirstName());
            user.setLastName(updatedDetails.getLastName());
            user.setPhoneNumber(updatedDetails.getPhoneNumber());
            return userRepository.save(user);
        }).orElse(null);
    }

    // 4. Fetch User by ID
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
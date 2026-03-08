package com.shopease.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopease.bean.Status;
import com.shopease.bean.User;
import com.shopease.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getAllUsers")
	List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/getUser/{userId}")
	User getUserByUserId(@PathVariable("userId") long userId){
		System.out.println("Get User By User Id");
		return userService.getUserByUserId(userId);	
	}
	
	@GetMapping("/login")
	Status login(@RequestParam long userId, @RequestParam String password){
		System.out.println("login");
		return userService.checkLoginWithUserIdAndPassword(userId, password);	
	}
	
	@PostMapping("/createUser")
	Status createUser(@RequestBody User user) {
		System.out.println("Create User");
		return userService.createUser(user);
	}
	
	@PutMapping("/changePassword")
	Status changePassword(@RequestParam long userId, @RequestParam String oldPassword, @RequestParam String newPassword) {
		System.out.println("Change Password");
		return userService.changePassword(userId, oldPassword, newPassword);
	}
	
}

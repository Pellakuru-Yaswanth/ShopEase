package com.shopease.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopease.bean.Status;
import com.shopease.bean.User;
import com.shopease.dao.UserDao;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public List<User> getAllUsers(){
		return userDao.getAllUsers();
	}
	
	public Status checkLoginWithUserIdAndPassword(long userId, String password) {
		User user = getUserByUserId(userId);
		Status status = new Status();
		if(user==null) {
			status.setStatus(false);
			status.setMessage("User Id not found");
		} else if(!user.getPassword().equals(password)) {
			status.setStatus(false);
			status.setMessage("Password is incorrect");
		} else {
			status.setStatus(true);
			status.setMessage("User has been logged in successfully");
			user.setPassword("");
			status.setData(user);
		}
		return status;
	}
	
	public User getUserByUserId(long userId) {
		User user;
		try {
			user = userDao.getUserByUserId(userId);
		} catch (Exception e) {
			System.out.println("User does not exixts");
			user = null;
		}
		return user;
	}
	
	long generateUserId() {
		return userDao.generateUserId();
	}
	
	synchronized public Status createUser(User user) {
		long userId = generateUserId();
		user.setUserId(userId);
		Status status = new Status();
		try{
			 status.setStatus(userDao.createUser(user));
			 if(status.isStatus()) {
				 status.setMessage("Account has been created successfully");
				 status.setData(userId);
			 } else status.setMessage("Something went wrong. Please try again later.");
		}catch(Exception e) {
			status.setStatus(false);
			status.setMessage("Unable to create acoount. Please try again later.");
		}
		return status;
	}
	
	public Status changePassword(long userId, String oldPassword, String newPassword) {
		Status status = checkLoginWithUserIdAndPassword(userId, oldPassword);
		if(status.getData()!=null) {
			status.setData(null);
			if(oldPassword.equals(newPassword)) {
				status.setStatus(false);
				status.setMessage("New Password should not be the current password");
			}
			else {
				boolean isPasswordChanged = userDao.changePassword(userId, newPassword);
				if(isPasswordChanged) {
					status.setStatus(true);
					status.setMessage("Password changed sucessfully");
				} else {
					status.setStatus(false);
					status.setMessage("Something went wrong. Please try again");
				}
			}
		}
		return status;
	}
	
}

package com.shopease.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shopease.bean.User;

@Repository
public class UserDao {
	
	@Autowired
	private JdbcTemplate repository;
	
	public List<User> getAllUsers(){
		String query = "SELECT * FROM USERS";
		try {
			return repository.query(query, new BeanPropertyRowMapper<>(User.class));
		} catch (Exception e) {
			return null;
		}
	}
	
	public User getUserByUserId(long userId) {
		String query = "SELECT * FROM USERS WHERE USER_ID = ?";
		try{
			return repository.queryForObject(query, new BeanPropertyRowMapper<>(User.class), userId);
		}catch(Exception e) {
			return null;
		}
		
	}
	
	public long generateUserId() {
		String query = "SELECT MAX(USER_ID) FROM USERS";
		long defaultId = 10000001;
		try {
			Long maxId = repository.queryForObject(query, Long.class);
			return maxId==null ? defaultId : maxId+1;
		} catch(Exception e) {
			return defaultId;
		}
	}
	
	public boolean createUser(User user) {
		String query = """
						INSERT INTO USERS 
						(USER_ID, PASSWORD, FULL_NAME, COUNTRY_CODE, PHONE_NUMBER, EMAIL) 
						VALUES(?, ?, ?, ?, ?, ?)
						""";
		return repository.update(query, user.getUserId(), user.getPassword(), user.getFullName(), user.getCountryCode(), user.getPhoneNumber(), user.getEmail())==1;
	}
	
	public boolean changePassword(long userId, String newPassword) {
		String query = "UPDATE USERS SET PASSWORD=? WHERE USER_ID=?";
		return repository.update(query, newPassword, userId)==1;
	}
	
}

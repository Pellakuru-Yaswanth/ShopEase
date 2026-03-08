package com.shopease.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	private long userId;
	private String password, fullName, countryCode;
	private long phoneNumber;
	private String email;
	
	public User() {}
	
	public User(long userId, String password, String fullName, String countryCode, long phoneNumber, String email) {
		super();
		this.userId = userId;
		this.password = password;
		this.fullName = fullName;
		this.countryCode = countryCode;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public long getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getFullName() {
		return fullName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getPhoneNumberWithCountryCode() {
		return countryCode+phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}

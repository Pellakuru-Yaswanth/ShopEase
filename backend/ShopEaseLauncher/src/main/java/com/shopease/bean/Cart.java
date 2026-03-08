package com.shopease.bean;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	
	private long userId;
	private Map<Item,Integer> cartItems;
	private double totalCartValue;
	
	public Cart(long userId){
		this.userId = userId;
		this.cartItems = new HashMap<>();
		this.totalCartValue = 0.0;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public Map<Item,Integer> getCartItems(){
		return cartItems;
	}
	
	public double getTotalCartValue() {
		return totalCartValue;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setCartItems(Map<Item, Integer> cartItems) {
		this.cartItems = cartItems;
	}

	public void setTotalCartValue(double totalCartValue) {
		this.totalCartValue = totalCartValue;
	}
	
}

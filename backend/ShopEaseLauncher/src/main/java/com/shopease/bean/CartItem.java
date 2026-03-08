package com.shopease.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cart")
public class CartItem {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartEntryId;
	
	private long userId, itemId;
	private int itemQuantity;
	
	public CartItem() {}
	
	public CartItem(long userId, long itemId, int itemQuantity) {
		super();
		this.userId = userId;
		this.itemId = itemId;
		this.itemQuantity = itemQuantity;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public long getCartEntryId() {
		return cartEntryId;
	}

	public void setCartEntryId(long cartEntryId) {
		this.cartEntryId = cartEntryId;
	}
	
}

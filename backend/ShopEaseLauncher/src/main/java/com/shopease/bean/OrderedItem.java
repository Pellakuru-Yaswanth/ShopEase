package com.shopease.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ordered_items")
public class OrderedItem {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long referenceId;
	
	private long orderId, userId, itemId;
	private int quantity;
	
	public OrderedItem() {}
	
	public OrderedItem(long orderId, long userId, long itemId, int quantity) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public long getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(long referenceId) {
		this.referenceId = referenceId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}

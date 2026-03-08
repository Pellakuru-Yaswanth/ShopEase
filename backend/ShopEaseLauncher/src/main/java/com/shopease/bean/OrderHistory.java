package com.shopease.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="order_history")
public class OrderHistory {

	@Id
	private long orderId;
	
	private long userId;
	private double payableAmount, paidAmount;
	private boolean isPaid;
	private String paymentMethod;
	
	public OrderHistory() {}
	
	public OrderHistory(long orderId, long userId, double payableAmount, double paidAmount, boolean isPaid, String paymentMethod) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.payableAmount = payableAmount;
		this.paidAmount = paidAmount;
		this.isPaid = isPaid;
		this.paymentMethod = paymentMethod;
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

	public double getPayableAmount() {
		return payableAmount;
	}

	public void setPayableAmount(double payableAmount) {
		this.payableAmount = payableAmount;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
}

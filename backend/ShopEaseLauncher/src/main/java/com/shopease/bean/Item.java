package com.shopease.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="items")
public class Item {
	
	@Id
	private long itemId;
	private String itemName;
	private String itemDescription;
	private int quantity;
	private double price;
	private String picture;
	
	public Item() {}
	
	public Item(long itemId, String itemName, String itemDescription, int quantity, double price, String picture) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.quantity = quantity;
		this.price = price;
		this.picture = picture;
	}

	public long getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}
	
	public String getItemDescription() {
		return itemDescription;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public String getPicture() {
		return picture;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
}

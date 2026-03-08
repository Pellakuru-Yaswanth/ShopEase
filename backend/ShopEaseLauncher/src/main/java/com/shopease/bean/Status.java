package com.shopease.bean;

public class Status {
	
	private boolean status = false;
	private String message;
	private Object data = null;
	
	public Status() {}
	
	public Status(boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
}

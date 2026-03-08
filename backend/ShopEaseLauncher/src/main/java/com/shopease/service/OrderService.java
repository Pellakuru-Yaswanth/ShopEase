package com.shopease.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopease.bean.Cart;
import com.shopease.bean.Item;
import com.shopease.bean.OrderHistory;
import com.shopease.bean.OrderedItem;
import com.shopease.dao.OrderDao;
import com.shopease.dao.OrderHistoryDao;

@Service
public class OrderService {
	
	private OrderDao orderDao;
	private OrderHistoryDao orderHistoryDao;
	
	@Autowired
	public OrderService(OrderDao orderDao, OrderHistoryDao orderHistoryDao) {
		this.orderDao = orderDao;
		this.orderHistoryDao = orderHistoryDao;
	}
	
	synchronized public long generateOrderId() {
		long orderId;
		try {
			orderId = orderHistoryDao.generateOrderId()+1;
		}catch(Exception e) {
			orderId = 1000000001; //Default Order Id
		}
		return orderId;
	}
	
	synchronized public double placeOrder(Cart cart) {
		BigDecimal payableAmount = new BigDecimal(cart.getTotalCartValue());
		BigDecimal taxPercentage = new BigDecimal(0.18); //(18% => 18/100=0.18)
		payableAmount = payableAmount.add(payableAmount.multiply(taxPercentage)).setScale(2, RoundingMode.HALF_UP);
		double paidAmount = payableAmount.doubleValue();
		if(PaymentService.processPayment(paidAmount)) {
			long orderId = generateOrderId();
			orderHistoryDao.save(new OrderHistory(orderId, cart.getUserId(), paidAmount, paidAmount, true, "UPI Payment"));
			for(Map.Entry<Item, Integer> cartItemEntry : cart.getCartItems().entrySet()) {
				Item item = cartItemEntry.getKey();
				int quantity = cartItemEntry.getValue();
				orderDao.save(new OrderedItem(orderId, cart.getUserId(), item.getItemId(), quantity));
			}
		}
		return paidAmount;
	}
	
	
}

class PaymentService{
	
	public static boolean processPayment(double amount) {
		return true; //hard-coded
	}
	
}

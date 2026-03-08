package com.shopease.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopease.bean.OrderHistory;

@Repository
public interface OrderHistoryDao extends JpaRepository<OrderHistory, Long>{
	
	@Query("select max(orderId) from OrderHistory")
	long generateOrderId();
	
}
package com.shopease.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopease.bean.OrderedItem;

@Repository
public interface OrderDao extends JpaRepository<OrderedItem, Long>{
	
}

package com.shopease.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.shopease.bean.CartItem;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface CartDao extends JpaRepository<CartItem, Long>{
	
	List<CartItem> findCartItemsByUserId(long userId);
	
	CartItem findCartItemByUserIdAndItemId(long userId, long itemId);
	
	@Modifying
	int deleteByUserId(long userId);
	
}

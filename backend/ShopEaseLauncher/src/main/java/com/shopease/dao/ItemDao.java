package com.shopease.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopease.bean.Item;

@Repository
@Transactional
public interface ItemDao extends JpaRepository<Item, Long>{
	
	@Query("select i from Item i")
	public List<Item> findAllItems();
	
	public Item findItemByItemId(long itemId);
	
	public List<Item> findItemsByItemName(String itemName);
	
	@Query("select max(itemId) from Item")
	public long generateItemId();
	
}

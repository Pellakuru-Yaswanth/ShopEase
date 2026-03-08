package com.shopease.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopease.bean.Item;
import com.shopease.bean.Status;
import com.shopease.dao.ItemDao;

@Service
public class ItemService {

	@Autowired
	private ItemDao itemDao;
	
	public List<Item> getAllItems(){
		return itemDao.findAllItems();
	}
	
	public Item getItemByItemId(long itemId) {
		return itemDao.findItemByItemId(itemId);
	}
	
	public List<Item> getItemsByItemName(String itemName) {
		return itemDao.findItemsByItemName(itemName);
	}
	
	synchronized public long generateItemId() {
		long itemId;
		try{
			itemId = itemDao.generateItemId()+1;
		}catch(Exception e) {
			itemId = 100001;
		}
		return itemId;
	}
	
	synchronized public Status createItem(Item item) {
		long itemId = generateItemId();
		Status status = new Status(true, itemId+"");
		item.setItemId(itemId);
		try {
			itemDao.save(item);
			status.setMessage(itemId+" "+item.getItemName().equals(getItemByItemId(itemId).getItemName()));
		}catch(Exception e) {
			System.out.println(e);
			status.setMessage(-1+"");
			status.setStatus(false);
		}
		return status;
	}
	
	synchronized public Status updateItem(Item item) {
		Item isItemUpdated = itemDao.save(item);
		boolean isUpdated = isItemUpdated!=null;
		return new Status(isUpdated, isUpdated ? "Item has been updated successfully" : "Something went wrong");
	}
}

package com.shopease.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopease.bean.Item;
import com.shopease.bean.Status;
import com.shopease.service.ItemService;

@RestController
@RequestMapping("/item")
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/getItemById/{itemId}")
	Item getItemByItemId(@PathVariable("itemId") long itemId){
		System.out.println("Get Item By Item Id");
		return itemService.getItemByItemId(itemId);	
	}
	
	@GetMapping("/getItemsByName/{itemName}")
	List<Item> getItemsByItemName(@PathVariable("itemName") String itemName){
		System.out.println("Get Items By Item Name");
		return itemService.getItemsByItemName(itemName);
	}
	
	@PostMapping("/createItem")
	Status createItem(@RequestBody Item item) {
		System.out.println("Create Item");
		return itemService.createItem(item);
	}
	
	@PutMapping("/updateItem")
	Status updateItemName(@RequestBody Item item) {
		System.out.println("Update Item Name");
		return itemService.updateItem(item);
	}
	
}

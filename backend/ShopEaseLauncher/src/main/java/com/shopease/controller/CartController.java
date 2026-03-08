package com.shopease.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopease.bean.Cart;
import com.shopease.bean.Status;
import com.shopease.service.CartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/getCart/{userId}")
	Cart getCartByUserId(@PathVariable long userId) {
		return cartService.getCartByUserId(userId);
	}
	
	@PostMapping("/addItem")
	Status addItemToCart(@RequestParam long userId, @RequestParam long itemId) {
		return cartService.addItemToCart(userId, itemId);
	}
	
	@PostMapping("/removeItem")
	Status removeItemFromCart(@RequestParam long userId, @RequestParam long itemId) {
		return cartService.removeItemFromCart(userId, itemId);
	}
	
	@GetMapping("/checkout/{userId}")
	Status checkout(@PathVariable long userId) {
		return cartService.checkout(userId);
	}
}

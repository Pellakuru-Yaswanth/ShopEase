package com.shopease.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopease.bean.Cart;
import com.shopease.bean.CartItem;
import com.shopease.bean.Item;
import com.shopease.bean.Status;
import com.shopease.dao.CartDao;

@Service
public class CartService {
	
	private CartDao cartDao;
	private ItemService itemService;
	private UserService userService;
	private OrderService orderService;
	
	@Autowired
	public CartService(CartDao cartDao, UserService userService, ItemService itemService, OrderService orderService) {
		this.cartDao = cartDao;
		this.userService = userService;
		this.itemService = itemService;
		this.orderService = orderService;
	}

	public Cart getCartByUserId(long userId) {
		//returns Cart
		System.out.println("getCartByUserId");
		List<CartItem> cartItems = cartDao.findCartItemsByUserId(userId);
		Cart cart = new Cart(userId);
		if(cartItems.isEmpty()) return null;
		Map<Item, Integer> items = new HashMap<>();
		for(CartItem cartItem : cartItems) {
			Item item = itemService.getItemByItemId(cartItem.getItemId());
			items.put(item, cartItem.getItemQuantity());
		}
		cart.setCartItems(items);
		cart.setTotalCartValue(calculateTotalCartValue(cart));
		return cart;
	}
	
	public double calculateTotalCartValue(Cart cart) {
		System.out.println("calculateTotalCartValue");
		if(cart.getCartItems().isEmpty()) return 0.0;
		double totalCartValue = 0.0;
		for(Map.Entry<Item, Integer> cartItem: cart.getCartItems().entrySet()) {
			Item item = cartItem.getKey();
			int qty = cartItem.getValue();
			totalCartValue += item.getPrice()*qty;
		}
		return totalCartValue;
	}
	
	public Status addItemToCart(long userId, long itemId) {
		//returns Status Object
		System.out.println("addItemToCart");
		if(userService.getUserByUserId(userId)==null) return new Status(false, "User not found with User Id: "+userId);
		CartItem cartItem = cartDao.findCartItemByUserIdAndItemId(userId, itemId);
		Item item = itemService.getItemByItemId(itemId);
		if(item==null) return new Status(false, "Item not found with Item Id: "+itemId);
		if(item.getQuantity()==0) return new Status(false, "Item stock unavailable");
		if(cartItem==null) cartItem = new CartItem(userId, itemId, 1);
		else {
			if(cartItem.getItemQuantity()==item.getQuantity()) return new Status(false, "We have only "+item.getQuantity()+" items available");
			cartItem.setItemQuantity(cartItem.getItemQuantity()+1);
		}
		if(cartDao.save(cartItem)==null) return new Status(false, "Something went wrong");
		return new Status(true, "Item added successfully");
	}
	
	public Status removeItemFromCart(long userId, long itemId) {
		//returns Status Object
		System.out.println("removeItemFromCart");
		CartItem cartItem = cartDao.findCartItemByUserIdAndItemId(userId, itemId);
		if(cartItem==null) return new Status(false, "Item not found with Id: "+itemId+" in specified cart with User Id: "+userId);
		if(cartItem.getItemQuantity()==1) cartDao.delete(cartItem);
		else {
			cartItem.setItemQuantity(cartItem.getItemQuantity()-1);
			cartDao.save(cartItem);
		}
		return new Status(true, "Item removed successfully");
	}
	
	public Status checkout(long userId) {
		//returns Status Object
		System.out.println(System.currentTimeMillis());
		System.out.println("checkout");
		Cart cart = getCartByUserId(userId);
		if(cart==null) return new Status(false, "Order value should be greater than zero.");
		double paidAmount = orderService.placeOrder(cart);
		cartDao.deleteByUserId(userId);
		for(Map.Entry<Item, Integer> cartItem : cart.getCartItems().entrySet()) {
			Item item = cartItem.getKey();
			int qty = cartItem.getValue();
			item.setQuantity(item.getQuantity()-qty);
			itemService.updateItem(item);
		}
		System.out.println(System.currentTimeMillis());
		return new Status(true, "Your order has been placed with cart value: "+cart.getTotalCartValue()+" and order value: "+paidAmount);
	}
	
}

package com.shopease.controller;

import com.shopease.bean.shopping.Cart;
import com.shopease.bean.catalog.Product;
import com.shopease.bean.identity.User;
import com.shopease.service.CartService;
import com.shopease.service.ProductService;
import com.shopease.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    // 1. GET: View User's Cart
    // Endpoint: http://localhost:8080/api/cart/user/1
    @GetMapping("/user/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(cartService.getCartByUser(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 2. POST: Add Item to Cart
    // Body: { "userId": 1, "productId": 10, "quantity": 2 }
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody Map<String, Long> payload) {
        User user = userService.getUserById(payload.get("userId"));
        Product product = productService.getProductById(payload.get("productId"));
        int quantity = payload.get("quantity").intValue();

        if (user != null && product != null) {
            cartService.addItemToCart(user, product, quantity);
            return new ResponseEntity<>("Product added to cart", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to add item", HttpStatus.BAD_REQUEST);
    }

    // 3. DELETE: Remove Item from Cart
    // Endpoint: http://localhost:8080/api/cart/remove/5 (CartItem ID)
    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long cartItemId) {
        cartService.removeItem(cartItemId);
        return new ResponseEntity<>("Item removed from cart", HttpStatus.OK);
    }
}
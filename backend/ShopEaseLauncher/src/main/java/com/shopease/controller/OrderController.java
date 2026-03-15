package com.shopease.controller;

import com.shopease.bean.identity.Address;
import com.shopease.bean.checkout.Order;
import com.shopease.bean.identity.User;
import com.shopease.service.OrderService;
import com.shopease.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    // 1. POST: Place an Order (Checkout)
    // Endpoint: http://localhost:8080/api/orders/checkout
    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody Map<String, Object> payload) {
        try {
            Long userId = Long.valueOf(payload.get("userId").toString());
            String paymentMethod = (String) payload.get("paymentMethod");
            
            // In a full app, you'd fetch the actual Address object by ID
            User user = userService.getUserById(userId);
            Address address = user.getAddresses().get(0); //default address
            
            // For this example, we assume a simple address link or default
            Order order = orderService.placeOrder(user, address, paymentMethod);
            
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 2. GET: Fetch Order History for a User
    // Endpoint: http://localhost:8080/api/orders/history/1
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable Long userId) {
        return new ResponseEntity<>(orderService.getUserOrders(userId), HttpStatus.OK);
    }

    // 3. PUT: Update Order Status (Admin feature)
    // Endpoint: http://localhost:8080/api/orders/update/status
    @PutMapping("/update/status")
    public ResponseEntity<Order> updateStatus(@RequestBody Map<String, String> payload) {
        Long orderId = Long.valueOf(payload.get("orderId"));
        String status = payload.get("status");
        
        Order updatedOrder = orderService.updateOrderStatus(orderId, status);
        return updatedOrder != null ? 
            new ResponseEntity<>(updatedOrder, HttpStatus.OK) : 
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
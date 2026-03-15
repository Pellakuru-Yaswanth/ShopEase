package com.shopease.service;

import com.shopease.bean.checkout.*;
import com.shopease.bean.catalog.*;
import com.shopease.bean.shopping.*;
import com.shopease.bean.identity.*;
import com.shopease.repository.checkout.*;
import com.shopease.repository.catalog.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartService cartService;

    // 1. Place Order (The most important method)
    @Transactional
    public Order placeOrder(User user, Address shippingAddress, String paymentMethod) throws Exception {
        Cart cart = cartService.getCartByUser(user);
        
        if (cart.getItems().isEmpty()) {
            throw new Exception("Cannot place order with an empty cart!");
        }

        // Create new Order object
        Order order = new Order();
        order.setUser(user);
        order.setShippingAddress(shippingAddress.toString());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
        order.setTotalAmount(cartService.calculateTotal(cart));
        
        // Save order first to get the ID
        Order savedOrder = orderRepository.save(order);

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();
            
            // Check Stock Availability
            if (product.getStockQuantity() < cartItem.getQuantity()) {
                throw new Exception("Product " + product.getName() + " is out of stock!");
            }

            // Deduct Stock
            product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
            productRepository.save(product);

            // Create OrderItem (Snapshot of product at purchase)
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPriceAtPurchase(product.getPrice());
            
            orderItems.add(orderItemRepository.save(orderItem));
        }

        // Clear the user's cart after successful order creation
        cartService.clearCart(cart.getCartId());

        return savedOrder;
    }

    // 2. Get Order History for a User
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUser_UserIdOrderByOrderDateDesc(userId);
    }

    // 3. Update Order Status (Admin feature)
    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(status);
            return orderRepository.save(order);
        }
        return null;
    }
}
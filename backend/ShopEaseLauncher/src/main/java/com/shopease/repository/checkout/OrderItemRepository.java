package com.shopease.repository.checkout;

import com.shopease.bean.checkout.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // 1. Get all items belonging to a specific order
    // Useful for rendering a digital receipt or invoice
    List<OrderItem> findByOrder_OrderId(Long orderId);

    // 2. Find all instances of a specific product being sold
    // Useful for analytics (e.g., "How many times was iPhone 15 sold?")
    List<OrderItem> findByProduct_ProductId(Long productId);

    // 3. Find items sold within a specific price range at purchase
    // Useful for financial auditing
    List<OrderItem> findByPriceAtPurchaseBetween(double minPrice, double maxPrice);
}
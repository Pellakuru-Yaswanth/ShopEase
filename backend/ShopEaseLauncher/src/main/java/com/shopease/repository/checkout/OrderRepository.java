package com.shopease.repository.checkout;

import com.shopease.bean.checkout.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // 1. Fetch Order History for a specific user
    // Sorted by date (newest first) so they see recent orders at the top
    List<Order> findByUser_UserIdOrderByOrderDateDesc(Long userId);

    // 2. Filter orders by Status (Admin functionality)
    // Useful for a dashboard showing "PENDING" or "SHIPPED" orders
    List<Order> findByStatus(String status);

    // 3. Find all orders for a user with a specific status
    // Example: "Show me all my 'DELIVERED' orders"
    List<Order> findByUser_UserIdAndStatus(Long userId, String status);

    // 4. Find orders placed after a certain date
    // Useful for "Sales in the last 30 days" reports
    List<Order> findByOrderDateAfter(java.time.LocalDateTime date);
}
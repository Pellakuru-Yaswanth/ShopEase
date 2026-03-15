package com.shopease.repository.checkout;

import com.shopease.bean.checkout.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // 1. Find payment by Transaction ID
    // Crucial for verifying responses from gateways like Stripe, PayPal, or Razorpay
    Optional<Payment> findByTransactionId(String transactionId);

    // 2. Find payment associated with a specific Order
    // Used to check if an order is safe to ship
    Optional<Payment> findByOrder_OrderId(Long orderId);

    // 3. Filter payments by status (e.g., "FAILED", "REFUNDED")
    // Useful for customer support and accounting
    List<Payment> findByStatus(String status);

    // 4. Find all payments made via a specific method (e.g., "UPI", "COD")
    List<Payment> findByPaymentMethod(String paymentMethod);
}
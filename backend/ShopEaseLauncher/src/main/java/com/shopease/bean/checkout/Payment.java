package com.shopease.bean.checkout;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column(nullable = false)
    private String paymentMethod; // e.g., "CREDIT_CARD", "UPI", "PAYPAL", "COD"

    @Column(name = "transaction_id", unique = true)
    private String transactionId; // The ID returned by the payment gateway

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String status; // e.g., "SUCCESS", "FAILED", "PENDING", "REFUNDED"

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    // Link back to the specific order
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // --- Lifecycle Hook ---
    @PrePersist
    protected void onCreate() {
        this.paymentDate = LocalDateTime.now();
    }

    // --- Constructors ---

    public Payment() {
    }

    public Payment(String paymentMethod, String transactionId, double amount, String status, Order order) {
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
        this.amount = amount;
        this.status = status;
        this.order = order;
    }

    // --- Getters and Setters ---

    public Long getPaymentId() { return paymentId; }
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
}
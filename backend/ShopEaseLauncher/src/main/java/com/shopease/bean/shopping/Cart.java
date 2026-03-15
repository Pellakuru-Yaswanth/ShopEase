package com.shopease.bean.shopping;

import com.shopease.bean.identity.User;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    // One user has one cart (One-to-One)
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // A cart contains many items
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    // --- Constructors ---

    public Cart() {
    }

    public Cart(User user) {
        this.user = user;
    }

    // --- Getters and Setters ---

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    // Helper method to calculate total price of the cart
    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }
}
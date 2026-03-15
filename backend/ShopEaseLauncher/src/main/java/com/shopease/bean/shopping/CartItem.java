package com.shopease.bean.shopping;

import com.shopease.bean.catalog.Product;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // --- Constructors ---

    public CartItem() {
    }

    public CartItem(int quantity, Cart cart, Product product) {
        this.quantity = quantity;
        this.cart = cart;
        this.product = product;
    }

    // --- Getters and Setters ---

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // Helper method to calculate subtotal for this specific item
    public double getSubtotal() {
        if (product != null) {
            return product.getPrice() * this.quantity;
        }
        return 0.0;
    }
}
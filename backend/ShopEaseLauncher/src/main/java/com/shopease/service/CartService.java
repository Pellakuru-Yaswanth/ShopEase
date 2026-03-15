package com.shopease.service;

import com.shopease.bean.shopping.Cart;
import com.shopease.bean.shopping.CartItem;
import com.shopease.bean.catalog.Product;
import com.shopease.bean.identity.User;
import com.shopease.repository.shopping.CartItemRepository;
import com.shopease.repository.shopping.CartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    // 1. Get or Create Cart for a User
    public Cart getCartByUser(User user) {
        return cartRepository.findByUser_UserId(user.getUserId())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });
    }

    // 2. Add Item to Cart
    @Transactional
    public void addItemToCart(User user, Product product, int quantity) {
        Cart cart = getCartByUser(user);
        
        // Check if product already exists in cart
        Optional<CartItem> existingItem = cartItemRepository.findByCart_CartIdAndProduct_ProductId(
                cart.getCartId(), product.getProductId());

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartItemRepository.save(item);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            cartItemRepository.save(newItem);
        }
    }

    // 3. Remove Item from Cart
    public void removeItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    // 4. Clear Cart (After Checkout)
    @Transactional
    public void clearCart(Long cartId) {
        cartItemRepository.deleteByCart_CartId(cartId);
    }

    // 5. Calculate Total Price
    public double calculateTotal(Cart cart) {
        return cart.getItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }
}
package com.shopease.repository.identity;

import com.shopease.bean.identity.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    // 1. Get all saved addresses for a specific user
    // Useful for the "Select Shipping Address" page during checkout
    List<Address> findByUser_UserId(Long userId);

    // 2. Find addresses in a specific city or state
    // Useful for delivery availability checks or shipping calculations
    List<Address> findByCityIgnoreCase(String city);

    // 3. Find addresses by zip code
    List<Address> findByZipCode(String zipCode);
    
    // 4. Delete all addresses for a user (Maintenance/GDPR)
    void deleteByUser_UserId(Long userId);
}
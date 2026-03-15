package com.shopease.repository.identity;

import com.shopease.bean.identity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 1. Used for Login/Authentication
    Optional<User> findByEmail(String email);

    // 2. Used during Registration to prevent duplicate accounts
    boolean existsByEmail(String email);

    // 3. Used for Admin Dashboards to manage staff/managers
    List<User> findByIsAdminTrue();

    // 4. Used for searching users by name in an Admin User-Management table
    List<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
}
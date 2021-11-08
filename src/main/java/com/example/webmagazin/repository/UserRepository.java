package com.example.webmagazin.repository;

import com.example.webmagazin.entity.Product;
import com.example.webmagazin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByPhoneNumber(String phoneNumber);
    User findByPhoneNumber(String phoneNumber);
    void deleteByLikedProduct(Product product);
}

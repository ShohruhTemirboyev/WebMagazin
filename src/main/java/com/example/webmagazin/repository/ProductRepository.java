package com.example.webmagazin.repository;

import com.example.webmagazin.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsById(UUID uuid);
    Page<Product> findAllByProductType(Long productType, Pageable pageable);

}

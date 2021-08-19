package com.example.webmagazin.repository;

import com.example.webmagazin.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType,Integer> {
    boolean existsByName(String name);
}

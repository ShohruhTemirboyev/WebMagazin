package com.example.webmagazin.repository;

import com.example.webmagazin.entity.Exception;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExceptionRepository extends JpaRepository<Exception,Long> {
    Exception findByCode(Long code);
}

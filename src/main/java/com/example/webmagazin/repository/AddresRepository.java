package com.example.webmagazin.repository;

import com.example.webmagazin.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddresRepository extends JpaRepository<Address,Long> {

}

package com.example.webmagazin.repository;

import com.example.webmagazin.entity.Comentarya;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ComentaryaRepository extends JpaRepository<Comentarya,Long> {

    List<Comentarya> findAllByProductId(UUID productId);
}

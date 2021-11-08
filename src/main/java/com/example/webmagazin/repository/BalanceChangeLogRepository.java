package com.example.webmagazin.repository;

import com.example.webmagazin.entity.BalanceChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BalanceChangeLogRepository extends JpaRepository<BalanceChangeLog, UUID> {

}

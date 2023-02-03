package com.example.investment.stockorder.repository;

import com.example.investment.stockorder.model.StockOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StockOrderRepository extends JpaRepository<StockOrder, UUID> {
}

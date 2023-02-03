package com.example.investment.stockorder.service.query;

import com.example.investment.stockorder.dto.response.StockOrderResponseDto;
import com.example.investment.stockorder.model.StockOrder;
import com.example.investment.stockorder.repository.StockOrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class StockOrderQueryService {
  private final StockOrderRepository repository;

  public List<StockOrderResponseDto> findAll() {
    return repository.findAll().stream()
        .map(StockOrder::toResponseDto)
        .collect(Collectors.toList());
  }
}

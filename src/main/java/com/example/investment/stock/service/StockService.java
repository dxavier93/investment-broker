package com.example.investment.stock.service;

import com.example.investment.stock.repository.StockRepository;
import com.example.investment.stock.dto.response.StockResponseDto;
import com.example.investment.stock.model.Stock;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class StockService {
  private final StockRepository repository;

  public List<StockResponseDto> findAll() {
    return repository.findAll().stream()
        .map(Stock::toResponseDto)
        .collect(Collectors.toList());
  }
}

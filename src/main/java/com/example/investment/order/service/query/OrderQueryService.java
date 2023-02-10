package com.example.investment.order.service.query;

import com.example.investment.order.dto.response.OrderResponseDto;
import com.example.investment.order.model.Order;
import com.example.investment.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class OrderQueryService {
  private final OrderRepository repository;

  public List<OrderResponseDto> findAll() {
    return repository.findAll().stream()
        .map(Order::toResponseDto)
        .collect(Collectors.toList());
  }
}

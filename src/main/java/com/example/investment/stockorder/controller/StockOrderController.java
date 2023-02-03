package com.example.investment.stockorder.controller;

import com.example.investment.stockorder.dto.request.StockOrderRequestDto;
import com.example.investment.stockorder.dto.response.StockOrderResponseDto;
import com.example.investment.stockorder.model.StockOrder;
import com.example.investment.stockorder.service.StockOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stock-orders")
@Slf4j
public class StockOrderController {
  private final StockOrderService stockOrderService;

  @PostMapping(value = "/")
  @ResponseStatus(HttpStatus.CREATED)
  public StockOrderResponseDto create(
      @RequestBody StockOrderRequestDto stockOrderRequestDto) {
    return stockOrderService.create(stockOrderRequestDto).toResponseDto();
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<StockOrderResponseDto> findAll() {
    return stockOrderService.findAll()
        .stream()
        .map(StockOrder::toResponseDto)
        .collect(Collectors.toList());
  }
}

package com.example.investment.stock.controller;

import com.example.investment.stock.service.StockService;
import com.example.investment.stock.dto.response.StockResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stocks")
@Slf4j
public class StockController {
  private final StockService service;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<StockResponseDto> findAll() {
    return service.findAll();
  }
}

package com.example.investment.stockorder.controller;

import com.example.investment.stockorder.dto.request.StockOrderRequestDto;
import com.example.investment.stockorder.service.StockOrderService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stock-orders")
@Slf4j
public class StockOrderController {
  private final StockOrderService stockOrderService;

  @PostMapping
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Order created with success."),
      @ApiResponse(responseCode = "500", description = "Internal error.")
  })
  public ResponseEntity<String> create(
      @Valid @RequestBody StockOrderRequestDto stockOrderRequestDto) {
    return ResponseEntity.ok(stockOrderService.create());
  }

  @GetMapping
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Consulted with success."),
      @ApiResponse(responseCode = "500", description = "Internal error.")
  })
  public ResponseEntity<String> findAll() {
    return ResponseEntity.ok(stockOrderService.findAll());
  }
}

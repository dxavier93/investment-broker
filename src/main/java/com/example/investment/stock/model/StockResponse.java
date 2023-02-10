package com.example.investment.stock.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StockResponse {
  private String status;
  private Long StockOrderId;
}

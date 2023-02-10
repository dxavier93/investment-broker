package com.example.investment.account.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountResponse {
  private String status;
  private Long StockOrderId;
}

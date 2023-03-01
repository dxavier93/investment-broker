package com.example.investment.order.enuns;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public enum OrderStatusEnum {
  COMPLETE,
  ACCOUNT_OK,
  STOCK_OK,
  PENDING,
  FAIL
}

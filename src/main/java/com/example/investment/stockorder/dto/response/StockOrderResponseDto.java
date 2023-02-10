package com.example.investment.stockorder.dto.response;

import com.example.investment.stockorder.enuns.StockOrderStatusEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockOrderResponseDto {
  private Long stockOrderId;
  private float stockQuantity;
  private Long stockId;
  private Long accountId;
  private StockOrderStatusEnum status;
}

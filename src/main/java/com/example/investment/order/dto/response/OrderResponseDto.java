package com.example.investment.order.dto.response;

import com.example.investment.order.enuns.OrderStatusEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponseDto {
  private Long stockOrderId;
  private float stockQuantity;
  private Long stockId;
  private Long accountId;
  private OrderStatusEnum status;
}

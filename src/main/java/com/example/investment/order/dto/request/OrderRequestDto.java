package com.example.investment.order.dto.request;

import com.example.investment.order.model.Order;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.investment.order.enuns.OrderStatusEnum.PENDING;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto implements RequestDto<Order> {
  @NotNull
  private Long accountId;
  @NotNull
  private Long stockId;
  @NotNull
  private float stockQuantity;

  @Override
  public Order toEntity() {
    return Order.builder()
        .stockQuantity(this.stockQuantity)
        .stockId(this.stockId)
        .accountId(this.accountId)
        .status(PENDING)
        .build();
  }

  @Override
  public Order toEntity(Order order) {
    return null;
  }
}

package com.example.investment.order.model;

import com.example.investment.order.dto.response.OrderResponseDto;
import com.example.investment.order.enuns.OrderStatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TABLE_STOCK_ORDER")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements StandardModel{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  private float stockQuantity;
  @NotNull
  private Long stockId;
  @NotNull
  private Long accountId;
  @NotNull
  @Enumerated(EnumType.STRING)
  private OrderStatusEnum status;

  @Override
  public OrderResponseDto toResponseDto() {
    return OrderResponseDto.builder()
        .stockOrderId(id)
        .stockQuantity(stockQuantity)
        .stockId(stockId)
        .accountId(accountId)
        .status(status)
        .build();
  }
}

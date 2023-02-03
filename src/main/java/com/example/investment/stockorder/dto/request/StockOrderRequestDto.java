package com.example.investment.stockorder.dto.request;

import com.example.investment.stockorder.model.StockOrder;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class StockOrderRequestDto implements RequestDto<StockOrder> {
  @NotBlank
  private Long accountId;
  @NotBlank
  private Long stockId;
  @NotBlank
  private float stockQuantity;

  @Override
  public StockOrder toEntity() {
    return StockOrder.builder()
        .stockQuantity(this.stockQuantity)
        .stockId(this.stockId)
        .build();
  }

  @Override
  public StockOrder toEntity(StockOrder stockOrder) {
    return StockOrder.builder()
        .id(stockOrder.getId())
        .stockQuantity(this.stockQuantity)
        .stockId(this.stockId)
        .build();
  }
}

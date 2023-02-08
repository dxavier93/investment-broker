package com.example.investment.stockorder.dto.request;

import com.example.investment.stockorder.model.StockOrder;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

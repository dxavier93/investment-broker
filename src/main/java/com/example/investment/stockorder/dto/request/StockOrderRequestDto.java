package com.example.investment.stockorder.dto.request;

import com.example.investment.stockorder.enuns.StockOrderStatusEnum;
import com.example.investment.stockorder.model.StockOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static com.example.investment.stockorder.enuns.StockOrderStatusEnum.PENDING;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockOrderRequestDto implements RequestDto<StockOrder> {
  @NotNull
  private Long accountId;
  @NotNull
  private Long stockId;
  @NotNull
  private float stockQuantity;

  @Override
  public StockOrder toEntity() {
    return StockOrder.builder()
        .stockQuantity(this.stockQuantity)
        .stockId(this.stockId)
        .accountId(this.accountId)
        .status(PENDING)
        .build();
  }

  @Override
  public StockOrder toEntity(StockOrder stockOrder) {
    return null;
  }
}

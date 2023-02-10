package com.example.investment.stockorder.model;

import com.example.investment.stockorder.dto.response.StockOrderResponseDto;
import com.example.investment.stockorder.enuns.StockOrderStatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.investment.stockorder.enuns.StockOrderStatusEnum.COMPLETE;

@Entity
@Table(name = "TABLE_STOCK_ORDER")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockOrder implements StandardModel{
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
  private StockOrderStatusEnum status;

  @Override
  public StockOrderResponseDto toResponseDto() {
    return StockOrderResponseDto.builder()
        .stockOrderId(id)
        .stockQuantity(stockQuantity)
        .stockId(stockId)
        .accountId(accountId)
        .status(status)
        .build();
  }
}

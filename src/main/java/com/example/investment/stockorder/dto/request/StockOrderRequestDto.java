package com.example.investment.stockorder.dto.request;

import com.example.investment.stockorder.dto.StandardDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class StockOrderRequestDto implements StandardDto<Integer> {
  @NotBlank
  private UUID accountId;
  @NotBlank
  private UUID stockId;
  @NotBlank
  private float stockQuantity;

  @Override
  public Integer toEntity() {
    return null;
  }

  @Override
  public Integer toEntity(Integer integer) {
    return null;
  }
}

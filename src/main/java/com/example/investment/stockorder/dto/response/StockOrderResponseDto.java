package com.example.investment.stockorder.dto.response;

import com.example.investment.stockorder.dto.StandardDto;
import lombok.Data;

@Data
public class StockOrderResponseDto implements StandardDto<Integer> {
  private String status;

  @Override
  public Integer toEntity() {
    return null;
  }

  @Override
  public Integer toEntity(Integer integer) {
    return null;
  }
}

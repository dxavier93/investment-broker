package com.example.investment.stock.dto.request;

import com.example.investment.stock.model.Stock;
import com.example.investment.order.dto.request.RequestDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StockRequestDto implements RequestDto<Stock> {
  @NotBlank
  private String name;

  @Override
  public Stock toEntity() {
    return Stock.builder()
        .name(this.name)
        .build();
  }

  @Override
  public Stock toEntity(Stock entity) {
    return Stock.builder()
        .id(entity.getId())
        .name(this.name)
        .build();
  }
}

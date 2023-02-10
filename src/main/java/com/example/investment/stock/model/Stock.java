package com.example.investment.stock.model;

import com.example.investment.account.model.StandardModel;
import com.example.investment.stock.dto.response.StockResponseDto;
import jakarta.persistence.Entity;
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
@Table(name = "TABLE_STOCK")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock implements StandardModel<StockResponseDto> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  private String name;

  @Override
  public StockResponseDto toResponseDto() {
    return StockResponseDto.builder().name(this.name).build();
  }
}

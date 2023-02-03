package com.example.investment.stockorder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TABLE_STOCK_ORDER")
@Data
@Builder
public class StockOrder implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NotNull
  private UUID id;

  @NotNull
  private float stockQuantity;

  @NotNull
  private UUID stockId;
}

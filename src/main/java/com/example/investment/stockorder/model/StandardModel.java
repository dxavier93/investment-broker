package com.example.investment.stockorder.model;

import java.io.Serializable;

public interface StandardModel<T> extends Serializable {
  T toResponseDto();
}
package com.example.investment.order.model;

import java.io.Serializable;

public interface StandardModel<T> extends Serializable {
  T toResponseDto();
}
package com.example.investment.account.model;

import java.io.Serializable;

public interface StandardModel<T> extends Serializable {
  T toResponseDto();
}
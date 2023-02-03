package com.example.investment.stockorder.dto;

public interface StandardDto<T> {
  T toEntity();

  T toEntity(T t);
}
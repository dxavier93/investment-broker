package com.example.investment.stock.dto.request;

public interface RequestDto<T> {
  T toEntity();

  T toEntity(T t);
}
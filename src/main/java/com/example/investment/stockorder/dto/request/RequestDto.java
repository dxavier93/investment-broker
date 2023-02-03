package com.example.investment.stockorder.dto.request;

public interface RequestDto<T> {
  T toEntity();

  T toEntity(T t);
}
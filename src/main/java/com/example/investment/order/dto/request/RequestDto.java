package com.example.investment.order.dto.request;

public interface RequestDto<T> {
  T toEntity();

  T toEntity(T t);
}
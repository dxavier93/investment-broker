package com.example.investment.account.dto.request;

public interface RequestDto<T> {
  T toEntity();

  T toEntity(T t);
}
package com.example.investment.stockorder.service;

import com.example.investment.stockorder.dto.request.RequestDto;

import java.util.List;

public interface StandardService<T> {
  List<T> findAll();
  T create(RequestDto dto);
}

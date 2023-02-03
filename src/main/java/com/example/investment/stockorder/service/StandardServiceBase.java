package com.example.investment.stockorder.service;

import com.example.investment.stockorder.dto.request.RequestDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public abstract class StandardServiceBase<T> implements StandardService<T>{
  private JpaRepository<T, Long> repository;
  private String resourceName;

  public List<T> findAll() {
    return repository.findAll();
  }

  public T create(RequestDto dto) {
    return repository.save((T) dto.toEntity());
  }
}

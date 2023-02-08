package com.example.investment.account.service.query;

import com.example.investment.account.dto.response.AccountResponseDto;
import com.example.investment.account.model.Account;
import com.example.investment.account.repository.AccountRepository;
import com.example.investment.stockorder.dto.response.StockOrderResponseDto;
import com.example.investment.stockorder.model.StockOrder;
import com.example.investment.stockorder.repository.StockOrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class AccountQueryService {
  private final AccountRepository repository;

  public List<AccountResponseDto> findAll() {
    return repository.findAll().stream()
        .map(Account::toResponseDto)
        .collect(Collectors.toList());
  }
}

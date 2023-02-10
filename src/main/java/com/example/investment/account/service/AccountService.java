package com.example.investment.account.service;

import com.example.investment.account.dto.response.AccountResponseDto;
import com.example.investment.account.model.Account;
import com.example.investment.account.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class AccountService {
  private final AccountRepository repository;

  public List<AccountResponseDto> findAll() {
    return repository.findAll().stream()
        .map(Account::toResponseDto)
        .collect(Collectors.toList());
  }

  public void create(Account entity){
    repository.save(entity);
  }
}

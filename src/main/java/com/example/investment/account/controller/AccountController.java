package com.example.investment.account.controller;

import com.example.investment.account.dto.request.AccountRequestDto;
import com.example.investment.account.dto.response.AccountResponseDto;
import com.example.investment.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountController {
  private final AccountService service;

  @PostMapping(value = "/")
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody AccountRequestDto requestDto) {
    service.create(requestDto.toEntity());
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<AccountResponseDto> findAll() {
    return service.findAll();
  }
}

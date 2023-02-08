package com.example.investment.account.controller;

import com.example.investment.account.dto.request.AccountRequestDto;
import com.example.investment.account.dto.response.AccountResponseDto;
import com.example.investment.account.service.command.AccountCommandService;
import com.example.investment.account.service.query.AccountQueryService;
import com.example.investment.stockorder.dto.request.StockOrderRequestDto;
import com.example.investment.stockorder.dto.response.StockOrderResponseDto;
import com.example.investment.stockorder.service.command.StockOrderCommandService;
import com.example.investment.stockorder.service.query.StockOrderQueryService;
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
  private final AccountQueryService queryService;
  private final AccountCommandService commandService;

  @PostMapping(value = "/")
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody AccountRequestDto requestDto) {
    commandService.create(requestDto);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<AccountResponseDto> findAll() {
    return queryService.findAll();
  }
}

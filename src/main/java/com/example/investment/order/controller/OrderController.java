package com.example.investment.order.controller;

import com.example.investment.order.dto.request.OrderRequestDto;
import com.example.investment.order.dto.response.OrderResponseDto;
import com.example.investment.order.service.command.OrderCommandService;
import com.example.investment.order.service.query.OrderQueryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {
  private OrderQueryService queryService;
  private OrderCommandService commandService;

  @PostMapping(value = "/")
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody OrderRequestDto requestDto) {
    commandService.create(requestDto.toEntity());
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<OrderResponseDto> findAll() {
    return queryService.findAll();
  }

}

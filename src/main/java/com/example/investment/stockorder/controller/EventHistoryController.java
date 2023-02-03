package com.example.investment.stockorder.controller;

import com.example.investment.stockorder.dto.request.StockOrderRequestDto;
import com.example.investment.stockorder.dto.response.StockOrderResponseDto;
import com.example.investment.stockorder.model.EventHistory;
import com.example.investment.stockorder.repository.EventHistoryRepository;
import com.example.investment.stockorder.service.EventHistoryService;
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
@RequestMapping("/histories")
@Slf4j
public class EventHistoryController {
  private final EventHistoryService service;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<EventHistory> findAll() {
    return service.findAll();
  }
}

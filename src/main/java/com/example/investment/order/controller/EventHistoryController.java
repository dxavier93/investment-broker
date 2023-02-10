package com.example.investment.order.controller;

import com.example.investment.order.model.EventHistory;
import com.example.investment.order.service.EventHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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

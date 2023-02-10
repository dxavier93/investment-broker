package com.example.investment.order.service;

import com.example.investment.order.model.EventHistory;
import com.example.investment.order.repository.EventHistoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class EventHistoryService{
  private final EventHistoryRepository repository;

  public void create(String message, String status){
    repository.save(
        EventHistory.builder()
            .status(status)
            .message(message)
            .build()
    );
  }
  public List<EventHistory> findAll(){
    return repository.findAll();
  }
}

package com.example.investment.stockorder.service;

import com.example.investment.stockorder.dto.request.RequestDto;
import com.example.investment.stockorder.messaging.StockOrderProducer;
import com.example.investment.stockorder.model.EventHistory;
import com.example.investment.stockorder.model.StockOrder;
import com.example.investment.stockorder.repository.EventHistoryRepository;
import com.example.investment.stockorder.repository.StockOrderRepository;
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
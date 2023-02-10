package com.example.investment.stockorder.service.command;

import com.example.investment.stockorder.messaging.producer.StockOrderEventProducer;
import com.example.investment.stockorder.model.StockOrder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class StockOrderCommandService {
  private final StockOrderEventProducer eventProducer;

  public void create(StockOrder entity) {
    String message = String.format("Creating new stock order: [%s]", entity);
    log.info(message);
    eventProducer.send(entity);
  }
}

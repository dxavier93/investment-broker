package com.example.investment.order.service.command;

import com.example.investment.order.messaging.producer.OrderEventProducer;
import com.example.investment.order.model.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class OrderCommandService {
  private final OrderEventProducer eventProducer;

  public void create(Order entity) {
    String message = String.format("Creating new stock order: [%s]", entity);
    log.info(message);
    eventProducer.send(entity);
  }
}

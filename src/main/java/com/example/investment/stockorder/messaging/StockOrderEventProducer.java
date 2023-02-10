package com.example.investment.stockorder.messaging;

import com.example.investment.stockorder.model.StockOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StockOrderEventProducer {
  @Value(value = "${spring.kafka.order.event.topic.name}")
  private String topicEvent;
  @Autowired
  @Qualifier("stockOrderEventKafkaTemplate")
  private KafkaTemplate<String, String> templateEvent;
  @Autowired
  private ObjectMapper objectMapper;

  public void send(StockOrder entity) {
    log.info(String.format("Sending kafka message: [%s]", entity));
    try{
      String stockOrder = objectMapper.writeValueAsString(entity);
      templateEvent.send(topicEvent, stockOrder);
    }catch (Exception ex){
      log.error(String.format("Problems: %s",ex.getMessage()));
    }
  }
}

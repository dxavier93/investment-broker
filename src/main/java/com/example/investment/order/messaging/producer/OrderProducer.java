package com.example.investment.order.messaging.producer;

import com.example.investment.order.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderProducer {
  @Value(value = "${spring.kafka.order.topic.name}")
  private String topicOrder;
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;
  @Autowired
  private ObjectMapper objectMapper;

  public void send(Order entity) {
    log.info(String.format("Sending kafka message: [%s]", entity));
    try{
      String stockOrder = objectMapper.writeValueAsString(entity);
      kafkaTemplate.send(topicOrder, stockOrder);
    }catch (Exception ex){
      log.error(String.format("Problems: %s",ex.getMessage()));
    }
  }
}

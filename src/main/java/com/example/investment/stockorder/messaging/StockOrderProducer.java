package com.example.investment.stockorder.messaging;

import com.example.investment.stockorder.dto.request.StockOrderRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class StockOrderProducer {
  @Value(value = "${spring.kafka.order.event.topic.name}")
  private String topicEvent;

  @Value(value = "${spring.kafka.order.topic.name}")
  private String topicOrder;
  @Autowired
  @Qualifier("stockOrderEventKafkaTemplate")
  private KafkaTemplate<String, String> templateEvent;

  @Autowired
  @Qualifier("stockOrderKafkaTemplate")
  private KafkaTemplate<String, String> templateOrder;
  @Autowired
  private ObjectMapper objectMapper;

  public void send(StockOrderRequestDto requestDto) {
    log.info(String.format("Sending kafka message: [%s]", requestDto));
    try{
      String stockOrder = objectMapper.writeValueAsString(requestDto);
      templateEvent.send(topicEvent, stockOrder);
      templateOrder.send(topicOrder, stockOrder);
    }catch (Exception ex){
      log.error(String.format("Problems: %s",ex.getMessage()));
    }
  }
}

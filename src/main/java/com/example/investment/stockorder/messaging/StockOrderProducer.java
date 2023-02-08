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
  private String topicName;
  @Autowired
  @Qualifier("stockOrderKafkaTemplate")
  private KafkaTemplate<String, String> kafkaTemplate;
  @Autowired
  private ObjectMapper objectMapper;

  public void send(StockOrderRequestDto requestDto) {
    log.info("Sending kafka message " + requestDto);
    //CompletableFuture<SendResult<String, StockOrderRequestDto>> future =
    try{
      String event = objectMapper.writeValueAsString(requestDto);
      kafkaTemplate.send(topicName, event);
    }catch (Exception ex){
      log.error("Problems: "+ex.getMessage());
    }

    String u;
  }
}

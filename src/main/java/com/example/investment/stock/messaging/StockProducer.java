package com.example.investment.stock.messaging;

import com.example.investment.stock.model.StockResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StockProducer {
  @Value(value = "${spring.kafka.stock.response.topic.name}")
  private String topicName;
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;
  @Autowired
  private ObjectMapper objectMapper;

  public void sendResponse(StockResponse response) throws JsonProcessingException {
    log.info("Sending kafka message " + response);
    kafkaTemplate.send(topicName, objectMapper.writeValueAsString(response));
  }
}

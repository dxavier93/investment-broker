package com.example.investment.account.messaging;

import com.example.investment.account.model.AccountResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AccountProducer {
  @Value(value = "${spring.kafka.account.response.topic.name}")
  private String topicResponse;
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;
  @Autowired
  private ObjectMapper objectMapper;

  public void sendResponse(AccountResponse response) throws JsonProcessingException {
    log.info("Sending kafka message " + response);
    kafkaTemplate.send(topicResponse, objectMapper.writeValueAsString(response));
  }
}

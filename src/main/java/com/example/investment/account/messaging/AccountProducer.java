package com.example.investment.account.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AccountProducer {
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void send(String string) {
    log.info("Sending kafka message " + string);
    CompletableFuture<SendResult<String, String>> future =
        kafkaTemplate.send("topic_demo", string);
  }
}

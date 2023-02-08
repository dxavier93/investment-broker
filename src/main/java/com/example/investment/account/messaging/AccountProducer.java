package com.example.investment.account.messaging;

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
  @Value(value = "${spring.kafka.account.event.topic.name}")
  private String topicName;
  @Autowired
  @Qualifier("accountKafkaTemplate")
  private KafkaTemplate<String, String> kafkaTemplate;

  public void send(String string) {
    log.info("Sending kafka message " + string);
    CompletableFuture<SendResult<String, String>> future =
        kafkaTemplate.send(topicName, string);
  }
}

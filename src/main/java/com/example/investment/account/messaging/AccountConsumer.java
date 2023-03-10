package com.example.investment.account.messaging;

import com.example.investment.account.model.AccountResponse;
import com.example.investment.account.repository.AccountRepository;
import com.example.investment.order.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@AllArgsConstructor
public class AccountConsumer {
  private final AccountProducer producer;
  private final AccountRepository repository;
  private final ObjectMapper objectMapper;

  @KafkaListener(topics = "#{'${spring.kafka.order.topic.name}'}", groupId = "account")
  public void onReceiveAccount(ConsumerRecord event) throws IOException {
    String responseStatus = "FAILED";
    Order order =
        objectMapper.readValue(event.value().toString(), Order.class);

    log.info(String.format("Received Account Message: [%s]", order));

    if (repository.findById(order.getAccountId()).isPresent())
      responseStatus = "OK";

    producer.sendResponse(
        AccountResponse.builder()
            .StockOrderId(order.getId())
            .status(responseStatus)
            .build()
    );
  }
}

package com.example.investment.order.messaging.consumer;

import com.example.investment.account.model.AccountResponse;
import com.example.investment.order.enuns.OrderStatusEnum;
import com.example.investment.order.model.Order;
import com.example.investment.order.repository.OrderRepository;
import com.example.investment.stock.model.StockResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.investment.order.enuns.OrderStatusEnum.COMPLETE;
import static com.example.investment.order.enuns.OrderStatusEnum.FAIL;

@Component
@Slf4j
@AllArgsConstructor
public class OrderConsumer {
  private final OrderRepository repository;
  private final ObjectMapper objectMapper;

  @KafkaListener(topics = "#{'${spring.kafka.account.response.topic.name}'}", groupId = "1")
  public void onReceiveAccount(ConsumerRecord event) throws IOException {
    OrderStatusEnum status = FAIL;
    AccountResponse response =
        objectMapper.readValue(event.value().toString(), AccountResponse.class);

    log.info(String.format("Received Account Message: [%s]", response));

    Order order = repository.findById(response.getStockOrderId()).orElseThrow(
        () -> new ResourceNotFoundException("No stock order was found by this id.")
    );

    if (response.getStatus().equals("OK")) {
      status = COMPLETE;
    }

    log.info(String.format("OPERATION %s", status));
    order.setStatus(status);
    repository.save(order);
  }

  @KafkaListener(topics = "#{'${spring.kafka.stock.response.topic.name}'}", groupId = "1")
  public void onReceiveStock(ConsumerRecord event) throws IOException {
    OrderStatusEnum status = FAIL;
    StockResponse response =
        objectMapper.readValue(event.value().toString(), StockResponse.class);

    log.info(String.format("Received Stock Message: [%s]", response));

    Order order = repository.findById(response.getStockOrderId()).orElseThrow(
        () -> new ResourceNotFoundException("No Order was found by this id.")
    );
  }
}

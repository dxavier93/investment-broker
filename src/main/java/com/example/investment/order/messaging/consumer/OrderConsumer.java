package com.example.investment.order.messaging.consumer;

import com.example.investment.account.model.AccountResponse;
import com.example.investment.order.enuns.OrderStatusEnum;
import com.example.investment.order.model.Order;
import com.example.investment.order.repository.OrderRepository;
import com.example.investment.stock.model.StockResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.example.investment.order.enuns.OrderStatusEnum.COMPLETE;
import static com.example.investment.order.enuns.OrderStatusEnum.FAIL;

@Component
@Slf4j
public class OrderConsumer {
  private final OrderRepository repository;
  private final ObjectMapper objectMapper;

  private Queue<String> globalQueue;

  public OrderConsumer(OrderRepository repository, ObjectMapper objectMapper) {
    this.repository = repository;
    this.objectMapper = objectMapper;
    this.globalQueue = new ConcurrentLinkedQueue<>();
  }

  @KafkaListener(topics = "#{'${spring.kafka.account.response.topic.name}'}", groupId = "1")
  public void onReceiveAccount(ConsumerRecord event) throws IOException {
    AccountResponse response =
        objectMapper.readValue(event.value().toString(), AccountResponse.class);

    synchronized (this) {
      checkOrderStatus(response.getStatus(), response.getStockOrderId());
    }
  }

  @KafkaListener(topics = "#{'${spring.kafka.stock.response.topic.name}'}", groupId = "1")
  public void onReceiveStock(ConsumerRecord event) throws IOException {
    StockResponse response =
        objectMapper.readValue(event.value().toString(), StockResponse.class);

    log.info(String.format("Received Stock Message: [%s]", response));

    synchronized (this) {
      checkOrderStatus(response.getStatus(), response.getStockOrderId());
    }
  }

  private void checkOrderStatus(String orderStatus, long id) {
    OrderStatusEnum finalStatus = FAIL;

    synchronized (this) {

      globalQueue.add(orderStatus);

      log.info("Responses: " + globalQueue);

      if (globalQueue.size() > 1) {

        Order order = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No stock order was found by this id.")
        );

        if (!order.getStatus().equals(COMPLETE)) {

          if (globalQueue.stream().filter(it -> it.equals("OK")).count() == 2) {
            finalStatus = COMPLETE;
            globalQueue.clear();
          }

          order.setStatus(finalStatus);
          log.info(String.format("OPERATION %s", order.getStatus()));
          repository.save(order);
        }
      }
    }
  }
}

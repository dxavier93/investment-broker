package com.example.investment.order.messaging.consumer;

import com.example.investment.order.messaging.producer.OrderProducer;
import com.example.investment.order.model.Order;
import com.example.investment.order.repository.OrderRepository;
import com.example.investment.order.service.EventHistoryService;
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
public class OrderEventConsumer {
  private final EventHistoryService eventHistoryService;
  private final OrderRepository repository;
  private final OrderProducer producer;
  private final ObjectMapper objectMapper;

  @KafkaListener(topics = "#{'${spring.kafka.order.event.topic.name}'}", groupId = "1")
  public void onReceiveEvent(ConsumerRecord event) throws IOException {
    Order entity =
        objectMapper.readValue(event.value().toString(), Order.class);

    log.info(String.format("Received Message: [%s]", entity));

    eventHistoryService.create(entity.toString(), "CREATE");

    Order newOrder = repository.save(entity);

    producer.send(newOrder);
  }
}

package com.example.investment.stockorder.messaging.consumer;

import com.example.investment.stockorder.messaging.producer.StockOrderProducer;
import com.example.investment.stockorder.model.StockOrder;
import com.example.investment.stockorder.repository.StockOrderRepository;
import com.example.investment.stockorder.service.EventHistoryService;
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
public class StockOrderEventConsumer {
  private final EventHistoryService eventHistoryService;
  private final StockOrderRepository repository;
  private final StockOrderProducer producer;
  private final ObjectMapper objectMapper;

  @KafkaListener(topics = "#{'${spring.kafka.order.event.topic.name}'}", groupId = "1")
  public void onReceiveEvent(ConsumerRecord event) throws IOException {
    StockOrder entity =
        objectMapper.readValue(event.value().toString(), StockOrder.class);

    log.info(String.format("Received Message: [%s]", entity));

    eventHistoryService.create(entity.toString(), "CREATE");

    StockOrder newStockOrder = repository.save(entity);

    producer.send(newStockOrder);
  }
}

package com.example.investment.stock.messaging;

import com.example.investment.stock.repository.StockRepository;
import com.example.investment.order.model.Order;
import com.example.investment.stock.model.StockResponse;
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
public class StockConsumer {
  private final StockProducer producer;
  private final StockRepository repository;
  private final ObjectMapper objectMapper;

  @KafkaListener(topics = "#{'${spring.kafka.order.topic.name}'}", groupId = "stock")
  public void onReceiveStock(ConsumerRecord event) throws IOException {
    String responseStatus = "FAILED";
    Order order =
        objectMapper.readValue(event.value().toString(), Order.class);

    log.info(String.format("Received Stock Message: [%s]", order));

    if (repository.findById(order.getStockId()).isPresent())
      responseStatus = "OK";

    producer.sendResponse(
        StockResponse.builder()
            .StockOrderId(order.getId())
            .status(responseStatus)
            .build()
    );
  }
}

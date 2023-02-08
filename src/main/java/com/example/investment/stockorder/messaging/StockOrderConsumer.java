package com.example.investment.stockorder.messaging;

import com.example.investment.stockorder.dto.request.StockOrderRequestDto;
import com.example.investment.stockorder.model.StockOrder;
import com.example.investment.stockorder.repository.StockOrderRepository;
import com.example.investment.stockorder.service.EventHistoryService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Component
@Slf4j
@AllArgsConstructor
public class StockOrderConsumer {
  private final EventHistoryService eventHistoryService;
  private final StockOrderRepository repository;

  private final ObjectMapper objectMapper;
  @KafkaListener(topics = "#{'${spring.kafka.order.event.topic.name}'}", groupId = "1")
  public void onReceive(ConsumerRecord event) throws IOException {
    StockOrderRequestDto requestDto =
        objectMapper.readValue(event.value().toString(), StockOrderRequestDto.class);

    log.info(String.format("Received Message: [%s]", requestDto));

    eventHistoryService.create(requestDto.toString(), "CREATE");

    repository.save(requestDto.toEntity());

    log.info("### Event saved --> " + eventHistoryService.findAll());
  }
}

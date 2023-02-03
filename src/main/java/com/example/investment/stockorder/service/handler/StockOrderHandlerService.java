package com.example.investment.stockorder.service.handler;

import com.example.investment.stockorder.model.EventHistory;
import com.example.investment.stockorder.model.StockOrder;
import com.example.investment.stockorder.repository.EventHistoryRepository;
import com.example.investment.stockorder.repository.StockOrderRepository;
import com.example.investment.stockorder.service.EventHistoryService;
import com.example.investment.stockorder.service.query.StockOrderQueryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class StockOrderHandlerService {
  private final EventHistoryService eventHistoryService;
  private final StockOrderRepository repository;

  @KafkaListener(topics = "topic_demo", groupId = "1")
  public void onReceive(Object event) {
    ConsumerRecord consume = (ConsumerRecord) event;
    String value = (String) consume.value();

    log.info("Received Message: [ " + value + "]");

    eventHistoryService.create(value, "CREATE");

    repository.save(StockOrder.builder()
        .stockId(1L)
        .stockQuantity(1.0F)
        .build());

    log.info("### Event saved --> " + eventHistoryService.findAll());
  }
}

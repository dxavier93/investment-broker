package com.example.investment.stockorder.messaging;

import com.example.investment.account.model.AccountResponse;
import com.example.investment.stockorder.enuns.StockOrderStatusEnum;
import com.example.investment.stockorder.model.StockOrder;
import com.example.investment.stockorder.repository.StockOrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.investment.stockorder.enuns.StockOrderStatusEnum.COMPLETE;
import static com.example.investment.stockorder.enuns.StockOrderStatusEnum.FAIL;

@Component
@Slf4j
@AllArgsConstructor
public class StockOrderConsumer {
  private final StockOrderRepository repository;
  private final ObjectMapper objectMapper;

  @KafkaListener(topics = "#{'${spring.kafka.account.response.topic.name}'}", groupId = "1")
  public void onReceiveAccount(ConsumerRecord event) throws IOException {
    StockOrderStatusEnum status = FAIL;
    AccountResponse response =
        objectMapper.readValue(event.value().toString(), AccountResponse.class);

    log.info(String.format("Received Account Message: [%s]", response));

    StockOrder stockOrder = repository.findById(response.getStockOrderId()).orElseThrow(
        () -> new ResourceNotFoundException("No stock order was found by this id.")
    );

    if (response.getStatus().equals("OK")) {
      status = COMPLETE;
    }

    log.info(String.format("OPERARION %s", status));
    stockOrder.setStatus(status);
    repository.save(stockOrder);
  }
}

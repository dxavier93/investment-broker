package com.example.investment.stockorder.service.command;

import com.example.investment.stockorder.dto.request.RequestDto;
import com.example.investment.stockorder.dto.request.StockOrderRequestDto;
import com.example.investment.stockorder.messaging.StockOrderProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class StockOrderCommandService {
  private final StockOrderProducer producer;

  public void create(StockOrderRequestDto requestDto) {
    String message = "Creating new stock order: [" + requestDto.toString() + "]";
    log.info(message);
    producer.send(requestDto);
  }
}

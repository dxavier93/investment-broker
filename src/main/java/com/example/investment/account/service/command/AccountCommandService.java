package com.example.investment.account.service.command;

import com.example.investment.account.messaging.AccountProducer;
import com.example.investment.stockorder.dto.request.RequestDto;
import com.example.investment.stockorder.messaging.StockOrderProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AccountCommandService {
  private final AccountProducer producer;

  public void create(RequestDto requestDto){
    String message = "Creating new account: ["+requestDto.toString()+"]";
    log.info(message);
    producer.sendEvent(message);
  }
}

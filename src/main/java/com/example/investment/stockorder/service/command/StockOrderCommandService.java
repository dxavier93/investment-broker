package com.example.investment.stockorder.service.command;

import com.example.investment.stockorder.dto.request.RequestDto;
import com.example.investment.stockorder.messaging.StockOrderProducer;
import com.example.investment.stockorder.model.StockOrder;
import com.example.investment.stockorder.repository.StockOrderRepository;
import com.example.investment.stockorder.service.StandardServiceBase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class StockOrderCommandService {
  private final StockOrderProducer producer;

  public void create(RequestDto dto){
    String message = "Creating new stock order: ["+dto.toString()+"]";
    log.info(message);
    producer.send(message);
  }
}

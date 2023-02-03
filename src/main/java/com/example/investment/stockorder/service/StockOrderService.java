package com.example.investment.stockorder.service;

import com.example.investment.stockorder.dto.request.RequestDto;
import com.example.investment.stockorder.messaging.StockOrderProducer;
import com.example.investment.stockorder.model.StockOrder;
import com.example.investment.stockorder.repository.StockOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StockOrderService extends StandardServiceBase<StockOrder>{
  private static final String resourceName = "Stock order";
  private final StockOrderProducer producer;
  StockOrderService(StockOrderRepository repository, StockOrderProducer producer){
    super(repository, resourceName);
    this.producer = producer;
  }

  public StockOrder create(RequestDto dto){
    return super.create(dto);
  }
  public List<StockOrder> findAll(){
    producer.send("Testando producer...");
    return super.findAll();
  }
}

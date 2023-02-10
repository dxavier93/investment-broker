package com.example.investment.order.service;

import org.springframework.stereotype.Service;

@Service
public class OrderHandlerService {
  public String process(String account, String stock){
    if(account.equals(stock)){
      return "OK";
    }
    return "NOP";
  }
}

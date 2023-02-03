package com.example.investment.stockorder.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

@Service
@Slf4j
public class StockOrderProducer {
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void send(String string) {
    CompletableFuture<SendResult<String, String>> future =
        kafkaTemplate.send("topic_demo", string);

    future.whenComplete(new BiConsumer() {

      @Override
      public void accept(Object o, Object o2) {
        log.info("Sent message=[" + string +
            "] with values =[" + o.toString() + "] and ["+o2.toString()+"]");
        future.complete(null);
      }
    });
  }
}

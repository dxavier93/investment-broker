package com.example.investment.order.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.StreamJoined;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.Stores;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.Executor;

@EnableKafkaStreams
@EnableAsync
@AllArgsConstructor
@Slf4j
public class OrderHandler {
  private OrderHandlerService service;

  @Bean
  public KStream<String, String> kstreamOrders(StreamsBuilder builder) {
    System.setProperty("spring.kafka.streams.state-dir", "/tmp/kafka-streams/"+ UUID.randomUUID());

    Serde<String> stringSerde = Serdes.String();
    JsonSerde<String> orderJsonSerde = new JsonSerde<>(String.class);

    KStream<String, String> orderStockStream = builder.stream("topic_account_response",
        Consumed.with(stringSerde, orderJsonSerde));
    KStream<String, String> orderPaymentStream = builder.stream("topic_stock_response",
        Consumed.with(stringSerde, orderJsonSerde));


    orderStockStream.join(orderPaymentStream, service::process, JoinWindows.of(Duration.ofSeconds(10)),
            StreamJoined.with(stringSerde, orderJsonSerde, orderJsonSerde))
        .peek((s, entityJoin) -> log.debug("order joined: {}",entityJoin))
        .to("orders");

    return orderStockStream;
  }
}

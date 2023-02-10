package com.example.investment.order.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.concurrent.Executor;

@EnableKafkaStreams
@EnableAsync
@AllArgsConstructor
@Slf4j
public class OrderHandler {
  private OrderHandlerService service;

  @Bean
  public KStream<Long, String> stream(StreamsBuilder builder) {
    JsonSerde<String> orderSerde = new JsonSerde<>(String.class);
    KStream<Long, String> stream = builder
        .stream("topic_account_response", Consumed.with(Serdes.Long(), orderSerde));

    stream.join(
            builder.stream("topic_stock_response"),
            service::process,
            JoinWindows.of(Duration.ofSeconds(10)),
            StreamJoined.with(Serdes.Long(), orderSerde, orderSerde))
        .peek((k, o) -> log.info("Output: " + o))
        .to("orders");

    return stream;
  }

  @Bean
  public KTable<Long, String> table(StreamsBuilder builder) {
    KeyValueBytesStoreSupplier store =
        Stores.persistentKeyValueStore("orders");
    JsonSerde<String> orderSerde = new JsonSerde<>(String.class);
    KStream<Long, String> stream = builder
        .stream("orders", Consumed.with(Serdes.Long(), orderSerde));
    return stream.toTable(Materialized.<Long, String>as(store)
        .withKeySerde(Serdes.Long())
        .withValueSerde(orderSerde));
  }

  @Bean
  public Executor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(5);
    executor.setMaxPoolSize(5);
    executor.setThreadNamePrefix("kafkaSender-");
    executor.initialize();
    return executor;
  }
}

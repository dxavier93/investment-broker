package com.example.investment.order.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class OrderKafkaConfig {
  private final KafkaProperties kafkaProperties;
  private final String bootstrapAddress;
  private final String topicStreamName;
  private final String topicName;
  private final String topicNameEvent;

  public OrderKafkaConfig(KafkaProperties kafkaProperties,
                          @Value(value = "${spring.kafka.bootstrap-servers}") String bootstrapAddress,
                          @Value(value = "${spring.kafka.order.stream.topic.name}") String topicStreamName,
                          @Value(value = "${spring.kafka.order.topic.name}") String topicName,
                          @Value(value = "${spring.kafka.order.event.topic.name}") String topicNameEvent) {
    this.kafkaProperties = kafkaProperties;
    this.bootstrapAddress = bootstrapAddress;
    this.topicStreamName = topicStreamName;
    this.topicName = topicName;
    this.topicNameEvent = topicNameEvent;
  }

  @Bean
  public KafkaAdmin stockOrderKafkaAdmin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    return new KafkaAdmin(configs);
  }

  @Bean
  public NewTopic stockOrderEventTopic() {
    return new NewTopic(topicNameEvent, 1, (short) 1);
  }

  @Bean
  public NewTopic stockOrderTopic() {
    return new NewTopic(topicName, 1, (short) 1);
  }

  @Bean
  public NewTopic createOrderTopic() {
    return TopicBuilder.name(topicStreamName)
        .partitions(3)
        .compact()
        .build();
  }

}

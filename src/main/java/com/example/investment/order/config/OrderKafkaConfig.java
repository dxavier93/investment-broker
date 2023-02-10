package com.example.investment.order.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class OrderKafkaConfig {
  @Value(value = "${spring.kafka.bootstrap-servers}")
  private String bootstrapAddress;

  @Value(value = "${spring.kafka.order.topic.name}")
  private String topicName;

  @Value(value = "${spring.kafka.order.event.topic.name}")
  private String topicNameEvent;

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
}

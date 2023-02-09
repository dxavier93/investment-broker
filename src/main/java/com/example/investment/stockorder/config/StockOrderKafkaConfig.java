package com.example.investment.stockorder.config;

import com.example.investment.stockorder.dto.request.StockOrderRequestDto;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class StockOrderKafkaConfig {
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
  public ProducerFactory<String, String> stockOrderProducerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  @Bean
  //@Qualifier("stockOrderKafkaTemplate")
  public KafkaTemplate<String, String> stockOrderEventKafkaTemplate() {
    return new KafkaTemplate<>(stockOrderProducerFactory());
  }

  @Bean
  //@Qualifier("stockOrderKafkaTemplate")
  public KafkaTemplate<String, String> stockOrderKafkaTemplate() {
    return new KafkaTemplate<>(stockOrderProducerFactory());
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

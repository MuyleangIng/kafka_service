package com.developerscambodia.devkhnotifationservice.config;

import com.developerscambodia.devkhnotifationservice.api.notification.Notification;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class NotificationConsumerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    // create consumer factory for course
    @Bean
    public ConsumerFactory<String , Object> consumerFactoryCourse(){

        Map<String , Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG , bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG ,"course-id");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG , StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG , JsonDeserializer.class);

        // handle error
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS , JsonDeserializer.class);
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS , false);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE ,  Notification.class.getName());

        return new DefaultKafkaConsumerFactory<>(props);

    }
    // create concurrentMessageListenerContainer
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String , Object> kafkaListenerContainerFactoryCourse(){

        ConcurrentKafkaListenerContainerFactory<String , Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactoryCourse());

        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);

        return factory;
    }
}

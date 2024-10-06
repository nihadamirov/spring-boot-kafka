package com.useraddressservice.config;

import com.useraddressservice.consumer.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class UserCreatedKafkaConsumerConfig {

    @Value("${kafka.host}")
    private String host;

    @Bean
    public ConsumerFactory<String, UserCreatedEvent> consumerFactory() {
        Map<String, Object> config = new HashMap<>();

        // Kafka broker ünvanı konfiqurasiyası
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        // JsonDeserializer ilə Kafka mesajlarını deserializasiya etmək
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class.getName());
        config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());

        // `UserCreatedEvent` tipini defolt olaraq təyin etmək
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, UserCreatedEvent.class.getName());
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*"); // Paketləri təhlükəsiz qəbul etmək

        return new DefaultKafkaConsumerFactory<>(
                config,
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(UserCreatedEvent.class))
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserCreatedEvent> concurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserCreatedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        // Kafka mesajlarını JSON formatında qəbul etmək üçün converter
        factory.setRecordMessageConverter(new StringJsonMessageConverter());
        return factory;
    }
}

//
//
//
//
//
//package com.useraddressservice.config;
//
//import com.useraddressservice.consumer.UserCreatedEvent;
//import lombok.RequiredArgsConstructor;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.support.converter.StringJsonMessageConverter;
//import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@RequiredArgsConstructor
//public class UserCreatedKafkaConsumerConfig {
//
//    @Value("${kafka.host}")
//    private String host;
//
//    public ConsumerFactory<String, UserCreatedEvent> consumerFactory() {
//
////        // Creating a Map of string-object pairs
////        Map<String, Object> config = new HashMap<>();
////
////        // Adding the Configuration
////        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
////        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
////        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
////        //config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
////
////        return new DefaultKafkaConsumerFactory<>(config);
//
//        // Kafka consumer üçün konfiqurasiya
//        Map<String, Object> config = new HashMap<>();
//
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class.getName());
//        config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
//        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, UserCreatedEvent.class.getName());
//        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//
//        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new ErrorHandlingDeserializer<>(new JsonDeserializer<>(UserCreatedEvent.class)));
//
//    }
//
////    // Listener yaratmaq
////    @Bean
////    public ConcurrentKafkaListenerContainerFactory<String, UserCreatedEvent> concurrentKafkaListenerContainerFactory() {
////        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
////        factory.setConsumerFactory(consumerFactory());
////        factory.setRecordMessageConverter(new StringJsonMessageConverter());
////        return factory;
////    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, UserCreatedEvent> concurrentKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, UserCreatedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        factory.setRecordMessageConverter(new StringJsonMessageConverter());
//        return factory;
//    }
//
//}

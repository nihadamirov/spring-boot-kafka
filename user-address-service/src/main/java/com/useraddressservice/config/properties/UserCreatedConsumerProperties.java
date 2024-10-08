package com.useraddressservice.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
//@ConfigurationProperties(prefix = "kafka.topics.user-created")
@Getter
@Setter
public class UserCreatedConsumerProperties {

    @Value("${kafka.topics.user-created.topics}")
    private String topic;

    @Value("${kafka.topics.user-created.consumerGroups}")
    private String consumerGroup;
}

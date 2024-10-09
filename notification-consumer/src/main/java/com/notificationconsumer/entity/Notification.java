package com.notificationconsumer.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

@Getter
@Setter
@Document
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = UNIQUE)
    private String id;
    @Field
    private Long userId;
    @Field
    private String email;
    private Boolean isSend;
}

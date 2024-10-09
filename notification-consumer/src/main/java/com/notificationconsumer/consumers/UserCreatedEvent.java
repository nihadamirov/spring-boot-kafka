package com.notificationconsumer.consumers;

import com.notificationconsumer.entity.Notification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreatedEvent {
    private Long id;
    private String email;
    private Boolean status;

    public static Notification EventToNotificationEntity(UserCreatedEvent event) {
        return Notification.builder()
                .userId(event.getId())
                .email(event.getEmail())
                .isSend(Boolean.TRUE)
                .build();
    }
}

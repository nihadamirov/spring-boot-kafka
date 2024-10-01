package com.useraddressservice.consumer;

import com.useraddressservice.entity.Address;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreatedEvent {
    private Long id;
    private String addressText;

    public static Address getAddressEntityFromEvent(UserCreatedEvent event) {
        return Address.builder()
                .userId(event.getId())
                .addressText(event.getAddressText())
                .build();
    }
}

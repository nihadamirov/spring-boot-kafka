package com.userservice.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDto {
    private Long id;
    private Long userId;
    private String addressText;
}
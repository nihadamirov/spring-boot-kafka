package com.userservice.dto.response;

import com.userservice.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse extends BaseResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private AddressResponseDto address;

    public static UserResponse getUserResponseWithAddress(User user, AddressResponseDto address) {
        UserResponse response = UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .address(address)
                .build();
        response.setId(user.getId());
        response.setCreatedAt(user.getCreatedAt());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }


}
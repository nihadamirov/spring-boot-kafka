package com.userservice.dto.request;

import com.userservice.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String addressText;
}
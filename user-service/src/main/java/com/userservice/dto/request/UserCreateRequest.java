package com.userservice.dto.request;

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
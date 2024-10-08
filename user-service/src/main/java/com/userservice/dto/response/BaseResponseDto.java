package com.userservice.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseResponseDto {
    private Long id;
    private Date createdAt;
    private Date updatedAt;
}
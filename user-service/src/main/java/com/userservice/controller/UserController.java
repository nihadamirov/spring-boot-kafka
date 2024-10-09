package com.userservice.controller;

import com.userservice.dto.response.AddressResponseDto;
import com.userservice.dto.request.UserCreateRequest;
import com.userservice.dto.response.UserResponse;
import com.userservice.entity.User;
import com.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public User create(@RequestBody UserCreateRequest userCreateRequest) {
        return userService.create(userCreateRequest);
    }

    @GetMapping("/{userId}")
    public UserResponse getUserAddress(@PathVariable Long userId) {
        String url = String.format("http://localhost:8802/api/address/%s", userId);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AddressResponseDto> address = restTemplate.getForEntity(url, AddressResponseDto.class);

        User user = userService.getUserById(address.getBody().getUserId());
        return UserResponse.getUserResponseWithAddress(user, address.getBody());

    }
}

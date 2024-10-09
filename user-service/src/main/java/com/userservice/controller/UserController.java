package com.userservice.controller;

import com.userservice.dto.request.UserCreateRequest;
import com.userservice.dto.response.UserResponse;
import com.userservice.entity.User;
import com.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserCreateRequest userCreateRequest) {
        return new ResponseEntity<>(userService.create(userCreateRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserAddress(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUserAddress(userId), HttpStatus.OK);
    }
}

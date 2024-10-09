package com.useraddressservice.controller;

import com.useraddressservice.entity.Address;
import com.useraddressservice.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/{userId}")
    public ResponseEntity<Address> getAddressByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(addressService.getAddressByUserId(userId), HttpStatus.OK);
    }
}
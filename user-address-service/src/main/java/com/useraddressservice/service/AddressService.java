package com.useraddressservice.service;

import com.useraddressservice.entity.Address;
import com.useraddressservice.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public void save(Address address) {
        addressRepository.save(address);
    }

    public Address getAddressByUserId(Long userId) {
        return addressRepository.findByUserId(userId);
    }
}

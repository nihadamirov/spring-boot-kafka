package com.userservice.service;

import com.userservice.config.UserCreatedTopicProperties;
import com.userservice.dto.UserCreatedPayload;
import com.userservice.dto.request.UserCreateRequest;
import com.userservice.dto.response.AddressResponseDto;
import com.userservice.dto.response.UserResponse;
import com.userservice.entity.User;
import com.userservice.producer.KafkaProducer;
import com.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.kafka.support.KafkaHeaders.KEY;
import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final KafkaProducer kafkaProducer;
    private final UserCreatedTopicProperties userCreatedTopicProperties;


    public User create(UserCreateRequest userCreateRequest) {
        User user = getUser(userCreateRequest);
        User savedUser = userRepository.save(user);

        UserCreatedPayload payload = UserCreatedPayload.GetUserCreatedPayload(savedUser, userCreateRequest.getAddressText());

        Map<String, Object> headers = new HashMap<>();
        headers.put(TOPIC, userCreatedTopicProperties.getTopicName());
        headers.put(KEY, savedUser.getId().toString());

        kafkaProducer.sendMessage(new GenericMessage<>(payload, headers));
        return savedUser;
    }

    public UserResponse getUserAddress(Long userId) {
        String url = String.format("http://localhost:8802/api/address/%s", userId);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AddressResponseDto> address = restTemplate.getForEntity(url, AddressResponseDto.class);

        User user = getUserById(address.getBody().getUserId());
        return UserResponse.getUserResponseWithAddress(user, address.getBody());
    }

    private User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    private User getUser(UserCreateRequest userCreateRequest) {
        return User.builder()
                .firstName(userCreateRequest.getFirstName())
                .lastName(userCreateRequest.getLastName())
                .email(userCreateRequest.getEmail())
                .build();
    }
}

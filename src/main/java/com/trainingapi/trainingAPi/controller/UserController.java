package com.trainingapi.trainingAPi.controller;

import com.trainingapi.trainingAPi.dto.request.CreateUserRequest;
import com.trainingapi.trainingAPi.dto.response.ApiResponse;
import com.trainingapi.trainingAPi.dto.response.CreateUserResponse;
import com.trainingapi.trainingAPi.repository.UserRepository;
import com.trainingapi.trainingAPi.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class UserController {
     UserService userService;

     @PostMapping
    public ApiResponse<CreateUserResponse> create(@RequestBody CreateUserRequest request){
        return  ApiResponse.<CreateUserResponse>builder()
                .data(userService.create(request))
                .build();
     }
}

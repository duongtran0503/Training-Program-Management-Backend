package com.trainingapi.trainingAPi.controller;

import com.trainingapi.trainingAPi.dto.request.LoginRequest;
import com.trainingapi.trainingAPi.dto.response.ApiResponse;
import com.trainingapi.trainingAPi.dto.response.AuthenticationResponse;
import com.trainingapi.trainingAPi.repository.UserRepository;
import com.trainingapi.trainingAPi.service.AuthenticationService;
import com.trainingapi.trainingAPi.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class AuthenticationController {
     AuthenticationService authenticationService;

     @PostMapping("/login")
     public ApiResponse<AuthenticationResponse> login(@Valid @RequestBody LoginRequest request) {
      return  ApiResponse.<AuthenticationResponse>builder().data(authenticationService.login(request))
              .statusCode(200)
              .isSuccess(true)
              .message("OK")
              .build();
     }



}

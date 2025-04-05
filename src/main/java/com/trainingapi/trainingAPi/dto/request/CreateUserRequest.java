package com.trainingapi.trainingAPi.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CreateUserRequest {
    String username;
    String password;
    String role;
}

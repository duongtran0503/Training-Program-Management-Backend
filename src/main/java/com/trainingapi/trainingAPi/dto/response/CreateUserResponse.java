package com.trainingapi.trainingAPi.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CreateUserResponse {
    String username;
    String role;
    Date createAt;
    Date updateAt;
}

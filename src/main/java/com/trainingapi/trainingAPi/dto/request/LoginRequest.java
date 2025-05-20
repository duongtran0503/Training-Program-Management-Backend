package com.trainingapi.trainingAPi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {
    @Size(min = 1,message = "Tên đăng nhập quá ngăn!")
    String username;
    @NotBlank(message = "Mật khẩu không được để trống!")
    String password;
}

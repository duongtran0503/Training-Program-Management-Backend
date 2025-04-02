package com.trainingapi.trainingAPi.mapper;

import com.trainingapi.trainingAPi.dto.request.CreateUserRequest;
import com.trainingapi.trainingAPi.dto.response.CreateUserResponse;
import com.trainingapi.trainingAPi.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(CreateUserRequest request);
    CreateUserResponse toUserResponse(User user);
}

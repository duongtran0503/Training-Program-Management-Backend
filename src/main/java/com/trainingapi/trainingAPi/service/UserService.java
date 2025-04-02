package com.trainingapi.trainingAPi.service;

import com.trainingapi.trainingAPi.dto.request.CreateUserRequest;
import com.trainingapi.trainingAPi.dto.response.CreateUserResponse;
import com.trainingapi.trainingAPi.entity.User;
import com.trainingapi.trainingAPi.exception.AppException;
import com.trainingapi.trainingAPi.exception.ErrorCode;
import com.trainingapi.trainingAPi.mapper.UserMapper;
import com.trainingapi.trainingAPi.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    public CreateUserResponse create(CreateUserRequest request) {
         if(userRepository.existsByUsername(request.getUsername()))
             throw  new AppException(ErrorCode.USER_EXISTED);
        User user = userMapper.toUser(request);
        return userMapper.toUserResponse(userRepository.save(user));
    }
}

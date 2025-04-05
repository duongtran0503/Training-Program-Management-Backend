package com.trainingapi.trainingAPi.service;

import com.trainingapi.trainingAPi.dto.request.CreateLecturerRequest;
import com.trainingapi.trainingAPi.dto.request.CreateUserRequest;
import com.trainingapi.trainingAPi.dto.request.UpdateLecturerRequest;
import com.trainingapi.trainingAPi.dto.response.CreateUserResponse;
import com.trainingapi.trainingAPi.dto.response.LecturerResponse;
import com.trainingapi.trainingAPi.entity.Lecturer;
import com.trainingapi.trainingAPi.entity.User;
import com.trainingapi.trainingAPi.exception.AppException;
import com.trainingapi.trainingAPi.exception.ErrorCode;
import com.trainingapi.trainingAPi.mapper.UserMapper;
import com.trainingapi.trainingAPi.repository.LecturerRepository;
import com.trainingapi.trainingAPi.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    LecturerRepository lecturerRepository;

    public CreateUserResponse create(CreateUserRequest request) {
         if(userRepository.existsByUsername(request.getUsername()))
             throw  new AppException(ErrorCode.USER_EXISTED);
        User user = userMapper.toUser(request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public LecturerResponse createLecturer(CreateLecturerRequest request) {
         if(lecturerRepository.existsByLecturerCode(request.getLecturerCode()))
              throw  new AppException(ErrorCode.USER_EXISTED);

        Lecturer lecturer = userMapper.toLecturer(request);

        return userMapper.toLecturerResponse(lecturerRepository.save(lecturer));
    }

    public List<LecturerResponse> getAllLecturer() {
         return  lecturerRepository.findAll().stream().map(userMapper::toLecturerResponse).toList();
    }

    public  LecturerResponse updateLecturer(UpdateLecturerRequest request,String id) {
       var lecturer = lecturerRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTED));
       if(lecturerRepository.existsByLecturerCode(request.getLecturerCode()))
          throw  new AppException(ErrorCode.LECTURER_CODE_EXISTED);

       userMapper.updateLecturerMapper(lecturer,request);

       return  userMapper.toLecturerResponse(lecturerRepository.save(lecturer));
    }

    public void deleteLecturer(String id) {
        if(!lecturerRepository.existsById(id))
            throw  new AppException(ErrorCode.USER_NOT_EXISTED);
        Lecturer lecturer = Lecturer.builder().status(false).id(id).build();
        lecturerRepository.save(lecturer);
    }

    public  LecturerResponse getLecturerById(String id) {
        var lecturer = lecturerRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTED));
        return  userMapper.toLecturerResponse(lecturer);
    }

    public List<LecturerResponse> findLecturerByName(String name) {

        return  lecturerRepository.findAllByName(name).stream().map(userMapper::toLecturerResponse).toList();

    }
}

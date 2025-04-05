package com.trainingapi.trainingAPi.mapper;

import com.trainingapi.trainingAPi.dto.request.CreateLecturerRequest;
import com.trainingapi.trainingAPi.dto.request.CreateUserRequest;
import com.trainingapi.trainingAPi.dto.request.UpdateLecturerRequest;
import com.trainingapi.trainingAPi.dto.response.CreateUserResponse;
import com.trainingapi.trainingAPi.dto.response.LecturerResponse;
import com.trainingapi.trainingAPi.entity.Lecturer;
import com.trainingapi.trainingAPi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(CreateUserRequest request);
    CreateUserResponse toUserResponse(User user);

    @Mappings({
            @Mapping(target = "status",constant = "true")
    })
    Lecturer toLecturer(CreateLecturerRequest request);
    @Mappings(
            {
                    @Mapping(target = "role",constant = "lecturer"),
            }
    )
    LecturerResponse toLecturerResponse(Lecturer lecturer);
    void updateLecturerMapper(@MappingTarget Lecturer lecturer, UpdateLecturerRequest request);

}

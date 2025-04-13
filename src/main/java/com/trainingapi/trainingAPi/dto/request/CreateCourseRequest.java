package com.trainingapi.trainingAPi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCourseRequest {
    @NotBlank(message = "Mã khóa học không được để trống")
    @Size(max = 50, message = "Mã khóa học không được vượt quá 10 ký tự")
     String courseCode;

    @NotBlank(message = "Tên khóa học không được để trống")
    @Size(max = 255, message = "Tên khóa học không được vượt quá 255 ký tự")
     String courseName;

    @Size(max = 1000, message = "Mô tả không được vượt quá 1000 ký tự")
     String description;

     boolean status;

     List<String> prerequisites;

}

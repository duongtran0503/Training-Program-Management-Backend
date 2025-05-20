package com.trainingapi.trainingAPi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class CreateCourseRequest {
    @NotBlank(message = "Mã học phần không được để trống")
    private String courseCode;

    @NotBlank(message = "Tên học phần không được để trống")
    private String courseName;

    @NotNull(message = "Số tín chỉ không được để trống")
    @Positive(message = "Số tín chỉ phải lớn hơn 0")
    private Integer credits;

    private String description;

    @NotNull(message = "Trạng thái không được để trống")
    private Boolean status;

    private List<String> prerequisites;
}

package com.trainingapi.trainingAPi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateCourseRequest {
    @NotBlank(message = "Tên khóa học không được để trống")
    @Size(max = 255, message = "Tên khóa học không được vượt quá 255 ký tự")
    private String courseName;

    @NotNull(message = "Số tín chỉ không được để trống")
    @Positive(message = "Số tín chỉ phải lớn hơn 0")
    private Integer credits;

    @Size(max = 1000, message = "Mô tả không được vượt quá 1000 ký tự")
    private String description;

    @NotNull(message = "Trạng thái không được để trống")
    private Boolean status;
}

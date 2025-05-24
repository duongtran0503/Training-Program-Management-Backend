package com.trainingapi.trainingAPi.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateTeachingPlanRequest {
    @NotBlank(message = "Mã kế hoạch không được để trống")
    String teachingPlanId;

    @Min(value = 2000, message = "Năm học phải lớn hơn hoặc bằng 2000")
    int academicYear;

    @Min(value = 1, message = "Học kỳ phải lớn hơn 0")
    int semester;

    @NotBlank(message = "Mã chương trình đào tạo không được để trống")
    String trainingProgramId;
}
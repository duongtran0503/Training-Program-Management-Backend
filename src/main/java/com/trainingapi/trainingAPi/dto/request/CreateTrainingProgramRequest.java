package com.trainingapi.trainingAPi.dto.request;

import com.trainingapi.trainingAPi.annotations.ValidValueStringDefine;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateTrainingProgramRequest {

    @NotBlank(message = "Mã chương trình không được để trống")
    String trainingProgramId;

    @NotBlank(message = "Tên chương trình không được để trống")
    String trainingProgramName;

    @NotBlank(message = "Cấp độ đào tạo không được để trống")
    String educationLevel;

    @ValidValueStringDefine(values = {"Cử nhân", "Tiến sĩ"}, message = "CTDT chỉ dành cho cử nhân và tiến sĩ")
    String degreeType;

    @NotBlank(message = "Hình thức đào tạo không được để trống")
    String trainingMode;

    @Min(value = 1, message = "Thời gian đào tạo phải lớn hơn 0")
    int trainingDuration;

    @Min(value = 1, message = "Tổng tín chỉ phải lớn hơn 0")
    int totalCredits;

    @NotBlank(message = "Khoa quản lý không được để trống")
    String managingFaculty;

    @NotBlank(message = "Ngôn ngữ giảng dạy không được để trống")
    String teachingLanguage;

    String issuingDecision;
}
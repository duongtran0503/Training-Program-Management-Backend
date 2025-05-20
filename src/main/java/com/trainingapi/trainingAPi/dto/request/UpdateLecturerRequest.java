package com.trainingapi.trainingAPi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateLecturerRequest {
    @NotBlank(message = "Tên giảng viên không được để trống")
    private String name;

    @NotBlank(message = "Mã giảng viên không được để trống")
    private String lecturerCode;

    @NotBlank(message = "Giới tính không được để trống")
    private String gender;

    private String titleAcademicRank;
    private String avatar;
    private String department;

    @NotNull(message = "Trạng thái không được để trống")
    private Boolean status;

    @NotNull(message = "Ngày sinh không được để trống")
    private LocalDate dob;

    @NotNull(message = "Ngày bắt đầu giảng dạy không được để trống")
    private LocalDate startDateOfTeaching;

    private LocalDate endDateOfTeaching;
}

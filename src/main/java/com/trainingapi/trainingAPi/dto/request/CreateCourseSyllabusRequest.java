package com.trainingapi.trainingAPi.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCourseSyllabusRequest {
    @NotBlank(message = "Nội dung giới thiệu học phần không được để trống")
    private String syllabusContent;

    private int theory = 0;
    private int practice = 0;
    private int credit = 0;
    @Valid
    private CreateEvaluationComponentsRequest evaluationComponents;
    @Data
    public  static  class CreateEvaluationComponentsRequest{
        @NotBlank(message = "Tên thành phần không được để trống!")
        private String componentName;
        private int ratio;
    }
}

package com.trainingapi.trainingAPi.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TeachingPlanResponse {
    String teachingPlanId;
    int academicYear;
    int semester;
    String trainingProgramId;
    LocalDateTime updateAt;
    LocalDateTime createAt;
}
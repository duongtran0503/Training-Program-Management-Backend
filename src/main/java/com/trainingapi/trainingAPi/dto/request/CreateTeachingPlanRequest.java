package com.trainingapi.trainingAPi.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateTeachingPlanRequest {
    String teachingPlanId;
    int semester;
    int academicYear;
    String trainingProgramId;
    Set<String> courseId;
}

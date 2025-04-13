package com.trainingapi.trainingAPi.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TeachingPlanResponse {
    String teachingPlanId;
    int semester;
    int academicYear;
    String academicYearString;

    List<CourseResponse> courseResponse;
}

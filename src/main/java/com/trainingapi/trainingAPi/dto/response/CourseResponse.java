package com.trainingapi.trainingAPi.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CourseResponse {
     String courseCode;
     String courseName;
     Integer credits;
     String description;
     boolean status;
    Set<PrerequisiteCourseResponse> prerequisites;

    @Data
    public static class PrerequisiteCourseResponse {
        private String courseCode;
        private String courseName;

    }



}

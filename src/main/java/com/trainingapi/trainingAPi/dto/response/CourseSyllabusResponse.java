package com.trainingapi.trainingAPi.dto.response;

import com.trainingapi.trainingAPi.entity.Course;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CourseSyllabusResponse {
     String syllabusId;
     String syllabusContent;
     int theory;
     int practice;
     int credit;
     EvaluationComponentsResponse evaluationComponents;
    CourseResponse courseResponse;
     @Data

     public  static  class EvaluationComponentsResponse{
         private String id;
         private String componentName;
         private int ratio;
     }
    Date createAt;
    Date updateAt;

}

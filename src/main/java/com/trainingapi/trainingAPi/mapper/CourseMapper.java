package com.trainingapi.trainingAPi.mapper;

import com.trainingapi.trainingAPi.dto.request.CreateCourseRequest;
import com.trainingapi.trainingAPi.dto.request.UpdateCourseRequest;
import com.trainingapi.trainingAPi.dto.response.CourseResponse;
import com.trainingapi.trainingAPi.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toCourse(CreateCourseRequest request);
    CourseResponse toCourseResponse(Course catalog);
    void updateCourse(@MappingTarget Course course , UpdateCourseRequest request);
}

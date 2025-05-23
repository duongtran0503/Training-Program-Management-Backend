package com.trainingapi.trainingAPi.mapper;

import com.trainingapi.trainingAPi.dto.request.CreateCourseSyllabusRequest;
import com.trainingapi.trainingAPi.dto.request.UpdateCourseSyllabusRequest;
import com.trainingapi.trainingAPi.dto.response.CourseSyllabusResponse;
import com.trainingapi.trainingAPi.entity.CourseSyllabus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CourseSyllabusMapper {
    CourseSyllabus toCourseSyllabus(CreateCourseSyllabusRequest request);
     @Mappings({
             @Mapping(source = "courseCode", target = "courseResponse"),
             @Mapping(source = "evaluationComponents", target = "evaluationComponents"),
             @Mapping(source = "status",target = "status")
     })
    CourseSyllabusResponse toCourseSyllabusResponse(CourseSyllabus courseSyllabus);
     void toCourseSyllabusUpdate(@MappingTarget  CourseSyllabus courseSyllabus, UpdateCourseSyllabusRequest request);
}

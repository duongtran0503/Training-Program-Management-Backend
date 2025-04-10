package com.trainingapi.trainingAPi.mapper;

import com.trainingapi.trainingAPi.dto.request.CreateTeachingPlanRequest;
import com.trainingapi.trainingAPi.dto.response.TeachingPlanResponse;
import com.trainingapi.trainingAPi.entity.TeachingPlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TeachingPlanMapper {
    @Mapping(target = "courses",ignore = true)
  TeachingPlan toTeachingPlan(CreateTeachingPlanRequest request);
  @Mappings({

          @Mapping(target = "courseResponse",source = "courses")
  })
  TeachingPlanResponse toTeachingPlanResponse(TeachingPlan teachingPlan);
}

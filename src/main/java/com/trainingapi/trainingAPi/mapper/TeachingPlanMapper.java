package com.trainingapi.trainingAPi.mapper;

import com.trainingapi.trainingAPi.dto.request.CreateTeachingPlanRequest;
import com.trainingapi.trainingAPi.dto.response.TeachingPlanResponse;
import com.trainingapi.trainingAPi.entity.TeachingPlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeachingPlanMapper {
    TeachingPlan toTeachingPlan(CreateTeachingPlanRequest request);

    @Mapping(target = "trainingProgramId", source = "trainingProgram.trainingProgramId")
    TeachingPlanResponse toTeachingPlanResponse(TeachingPlan teachingPlan);

    void updateTeachingPlan(@MappingTarget TeachingPlan teachingPlan, CreateTeachingPlanRequest request);
}
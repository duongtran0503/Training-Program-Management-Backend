package com.trainingapi.trainingAPi.mapper;

import com.trainingapi.trainingAPi.dto.request.CreateTrainingProgramRequest;
import com.trainingapi.trainingAPi.dto.request.UpdateTrainingProgramRequest;
import com.trainingapi.trainingAPi.dto.response.TrainingProgramResponse;
import com.trainingapi.trainingAPi.entity.TrainingProgram;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TrainingProgramMapper {
    @Mappings({
            @Mapping(target = "website",constant = "https://fit.sgu.edu.vn/site/"),
            @Mapping(target = "status",constant = "true")
    })
   TrainingProgram toTrainingProgram(CreateTrainingProgramRequest request);
    @Mapping(target = "status",source = "status")
   TrainingProgramResponse toTrainingProgramResponse(TrainingProgram trainingProgram);

   void updateTrainingProgram(@MappingTarget TrainingProgram trainingProgram, UpdateTrainingProgramRequest request);
}

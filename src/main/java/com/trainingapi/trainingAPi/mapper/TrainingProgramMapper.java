package com.trainingapi.trainingAPi.mapper;

import com.trainingapi.trainingAPi.dto.request.CreateKnowledgeBlockRequest;
import com.trainingapi.trainingAPi.dto.request.CreateTrainingProgramRequest;
import com.trainingapi.trainingAPi.dto.request.UpdateTrainingProgramRequest;
import com.trainingapi.trainingAPi.dto.response.KnowledgeBlockResponse;
import com.trainingapi.trainingAPi.dto.response.TrainingProgramResponse;
import com.trainingapi.trainingAPi.entity.KnowledgeBlock;
import com.trainingapi.trainingAPi.entity.KnowledgeBlockDetail;
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
    @Mappings({
            @Mapping(target = "status",source = "status"),
            @Mapping(target = "teachingPlanResponses",source = "teachingPlans"),
            @Mapping(target = "knowledgeBlocks",source = "knowledgeBlocks")

    })
   TrainingProgramResponse toTrainingProgramResponse(TrainingProgram trainingProgram);

   void updateTrainingProgram(@MappingTarget TrainingProgram trainingProgram, UpdateTrainingProgramRequest request);

    @Mappings({
            @Mapping(target = "knowledgeBlockDetailResponses", source = "knowledgeBlockDetails"),
            @Mapping(target = "TrainingCode", expression = "java(knowledgeBlock.getTrainingProgram().getTrainingProgramId())")
    })
    KnowledgeBlockResponse toKnowledgeBockResponse(KnowledgeBlock knowledgeBlock);
}

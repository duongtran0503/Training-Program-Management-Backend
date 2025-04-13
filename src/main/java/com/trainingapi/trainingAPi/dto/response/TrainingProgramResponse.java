package com.trainingapi.trainingAPi.dto.response;

import com.trainingapi.trainingAPi.entity.KnowledgeBlock;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TrainingProgramResponse {
    String trainingProgramId;
    String trainingProgramName;
    String educationLevel;
    String degreeType;
    String trainingMode;
    boolean status;
    int trainingDuration;
    int totalCredits;
    String managingFaculty;
    String teachingLanguage;
    String issuingDecision;
    String website;
    List<TeachingPlanResponse> teachingPlanResponses;
    List<KnowledgeBlock> knowledgeBlocks;
    LocalDateTime updateAt;
    LocalDateTime createAt;

    @Data
    public  static  class TeachingPlanResponse {
       private String teachingPlanId;
    }
}

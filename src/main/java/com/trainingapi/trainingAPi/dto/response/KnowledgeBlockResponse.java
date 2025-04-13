package com.trainingapi.trainingAPi.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class KnowledgeBlockResponse {
    String blockCode;
    String blockName;
    Integer requiredCredits;
    Integer electiveCredits;
    String TrainingCode;
    List<KnowledgeBlockDetailResponse> knowledgeBlockDetailResponses;
}

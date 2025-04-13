package com.trainingapi.trainingAPi.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class KnowledgeBlockDetailResponse {
    String id;
    String typeName;
    Integer requiredCredits;
    Integer electiveCredits;
}

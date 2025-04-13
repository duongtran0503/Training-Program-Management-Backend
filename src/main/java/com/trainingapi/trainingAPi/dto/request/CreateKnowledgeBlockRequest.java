package com.trainingapi.trainingAPi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateKnowledgeBlockRequest {
    String blockCode;
    String blockName;
    Integer requiredCredits;
    Integer electiveCredits;
    String trainingCode;
    @NotEmpty(message = "Danh sách chi tiết khối kiến thức không được trống!")
    List<KnowledgeBlockDetail> knowledgeBlockDetailList;

    @Data
    public static  class KnowledgeBlockDetail {
        String typeName;
        Integer requiredCredits;
        Integer electiveCredits;
    }
}

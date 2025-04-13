package com.trainingapi.trainingAPi.dto.request;

import com.trainingapi.trainingAPi.annotations.ValidValueStringDefine;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateTrainingProgramRequest {

    String trainingProgramId;
    String trainingProgramName;
    String educationLevel;
    @ValidValueStringDefine(values = {"Cử nhân","Tiến sĩ"},message = "CTDT chỉ dành cho cử nhân và tiến sĩ")
    String degreeType;
    String trainingMode;
    int trainingDuration;
    int totalCredits;
    String managingFaculty;
    String teachingLanguage;
    String issuingDecision;
}

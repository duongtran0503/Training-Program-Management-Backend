package com.trainingapi.trainingAPi.dto.response;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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
    LocalDateTime updateAt;
    LocalDateTime createAt;
}

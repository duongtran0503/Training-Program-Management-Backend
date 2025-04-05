package com.trainingapi.trainingAPi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainingProgram {
    @Id
    String curriculumId;
    String curriculumName;
    String educationLevel;
    String degreeType;
    String trainingMode;
    int trainingDuration;
    int totalCredits;
    String managingFaculty;
    String teachingLanguage;
    String website;
    String issuingDecision;


}

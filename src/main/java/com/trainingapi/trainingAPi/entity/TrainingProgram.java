package com.trainingapi.trainingAPi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainingProgram {
    @Id
    String trainingProgramId;
    String trainingProgramName;
    String educationLevel;
    String degreeType;
    String trainingMode;
    int trainingDuration;
    int totalCredits;
    String managingFaculty;
    String teachingLanguage;
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    boolean status;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'https://fit.sgu.edu.vn/site/'")
    String website;

    String issuingDecision;

    @UpdateTimestamp
    LocalDateTime updateAt;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    LocalDateTime createAt;


}

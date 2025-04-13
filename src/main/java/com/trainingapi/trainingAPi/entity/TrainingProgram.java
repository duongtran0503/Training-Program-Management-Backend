package com.trainingapi.trainingAPi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainingProgram {
    @Id
     @Column(name = "training_program_id")
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

    @OneToMany(mappedBy = "trainingProgram",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    List<TeachingPlan> teachingPlans;

    @OneToMany(mappedBy = "trainingProgram",cascade ={CascadeType.REMOVE,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH},fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    List<KnowledgeBlock> knowledgeBlocks;

    @UpdateTimestamp
    LocalDateTime updateAt;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    LocalDateTime createAt;


}

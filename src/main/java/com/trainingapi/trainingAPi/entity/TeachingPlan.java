package com.trainingapi.trainingAPi.entity;

import jakarta.persistence.*;
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
public class TeachingPlan {
    @Id
    @Column(name = "teaching_plan_id")
    String teachingPlanId;

    int academicYear;
    int semester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_program_id")
    TrainingProgram trainingProgram;

    @UpdateTimestamp
    LocalDateTime updateAt;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    LocalDateTime createAt;
}
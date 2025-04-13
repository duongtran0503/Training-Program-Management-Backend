package com.trainingapi.trainingAPi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

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
    int semester;
    int academicYear;

    @OneToMany(mappedBy = "teachingPlan",cascade =CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    List<Course> courses;

   @ManyToOne(fetch = FetchType.LAZY)
   @Fetch(FetchMode.JOIN)
   @JoinColumn(name = "training_program_id",referencedColumnName = "training_program_id")
   TrainingProgram trainingProgram;

}

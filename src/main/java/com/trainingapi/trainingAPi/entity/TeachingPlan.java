package com.trainingapi.trainingAPi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

    @OneToMany(mappedBy = "teachingPlan",cascade =CascadeType.ALL)
    List<Course> courses;

}

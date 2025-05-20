package com.trainingapi.trainingAPi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String id;

    @Column(name = "course_code", nullable = false, unique = true)
    String courseCode;

    @Column(name = "course_name", nullable = false)
    String courseName;

    @Column(nullable = false)
    Integer credits;

    String description;

    boolean status;

    @ManyToMany
    @JoinTable(
        name = "course_prerequisites",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "prerequisite_id")
    )
    @Builder.Default
    Set<Course> prerequisites = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "teaching_plan_id")
    TeachingPlan teachingPlan;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

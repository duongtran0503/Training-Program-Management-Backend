package com.trainingapi.trainingAPi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "lecturers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lecturer {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String id;

    @Column(nullable = false)
    String name;

    @Column(name = "lecturer_code", nullable = false, unique = true)
    String lecturerCode;

    @Column(nullable = false)
    String gender;

    @Column(name = "title_academic_rank")
    String titleAcademicRank;

    String avatar;

    String department;

    boolean status;

    @Column(name = "date_of_birth")
    LocalDate dob;

    @Column(name = "start_date_of_teaching")
    LocalDate startDateOfTeaching;

    @Column(name = "end_date_of_teaching")
    LocalDate endDateOfTeaching;

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

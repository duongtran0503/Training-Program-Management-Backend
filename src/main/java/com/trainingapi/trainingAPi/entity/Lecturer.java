package com.trainingapi.trainingAPi.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(length = 30)
    String lecturerCode;

    @Column(length = 30)
    String name;

    @CreationTimestamp
    Date startDateOfTeaching;

    Date endDateOfTeaching;

    String titleAcademicRank;
    String avatar;
    String department;
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    boolean status;

    Date dob;

    @Column(columnDefinition = "STRING DEFAULT NULL")
    String  gender;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime updateAt;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false,nullable = false)
    LocalDateTime  createAt;

}

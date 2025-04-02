package com.trainingapi.trainingAPi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(length = 30)
    String LectureCode;

    @Column(length = 30)
    String name;

    Date dob;

    @Column(columnDefinition = "BOOLEAN DEFAULT NULL")
    boolean isMale;

}

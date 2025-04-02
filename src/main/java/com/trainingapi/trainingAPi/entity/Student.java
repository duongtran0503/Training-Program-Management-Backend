package com.trainingapi.trainingAPi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(length = 10)
    String studentCode;

    @Column(length = 20)
    String name;

    @Column(columnDefinition = "BOOLEAN DEFAULT NULL")
    boolean isMale;

    @Column(length = 7)
    String  classCode;

}

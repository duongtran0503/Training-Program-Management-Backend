package com.trainingapi.trainingAPi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseCatalog {
    @Id
    String courseCode;
    String courseName;
    String credits;
    String description;
    String status;
}

package com.trainingapi.trainingAPi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EvaluationComponents {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   String id;

   String componentName;

   @Column(columnDefinition = "INT DEFAULT 0")
   int ratio;

   @OneToOne
   @JoinColumn(name = "syllabus_id",referencedColumnName = "syllabus_id")
    CourseSyllabus courseSyllabus;

    @UpdateTimestamp
    LocalDateTime  updateAt;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    LocalDateTime createAt;
}

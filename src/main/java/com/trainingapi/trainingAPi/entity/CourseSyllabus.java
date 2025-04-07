package com.trainingapi.trainingAPi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseSyllabus {
  @Id
   @Column(name = "syllabus_id")
   String syllabusId;
   String syllabusContent;
    @OneToOne
    @JoinColumn(name = "course_code", referencedColumnName = "course_code")
     Course courseCode;
}

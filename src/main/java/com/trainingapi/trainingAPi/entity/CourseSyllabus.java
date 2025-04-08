package com.trainingapi.trainingAPi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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

   @Column(columnDefinition = "INT DEFAULT 0")
   int theory;

   @Column(columnDefinition = "INT DEFAULT 0")
   int practice;

   @Column(columnDefinition = "INT DEFAULT 0")
   int credit;

   @OneToOne(mappedBy = "courseSyllabus",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
   @Fetch(FetchMode.JOIN)
   EvaluationComponents evaluationComponents;


    @OneToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "course_code", referencedColumnName = "course_code")
     Course courseCode;

 @UpdateTimestamp
 LocalDateTime updateAt;

 @CreationTimestamp
 @Column(updatable = false, nullable = false)
 LocalDateTime  createAt;
}

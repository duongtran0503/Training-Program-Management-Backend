package com.trainingapi.trainingAPi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    @Id
    @Column(name = "course_code")
    String courseCode;
    String courseName;
    Integer credits;
    String description;
    @Column(columnDefinition ="BOOLEAN DEFAULT TRUE")
    boolean status;

    @OneToOne(mappedBy = "courseCode",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
     CourseSyllabus courseSyllabus;

    @ManyToMany
    @JoinTable(name = "prerequisite",
            joinColumns=@JoinColumn(name = "course_code",referencedColumnName = "course_code"),
            inverseJoinColumns = @JoinColumn(name = "prerequisite_id", referencedColumnName = "course_code")
            )
    Set<Course> prerequisites;

   @ManyToMany(mappedBy = "prerequisites")
    Set<Course> prerequisiteOf;
}

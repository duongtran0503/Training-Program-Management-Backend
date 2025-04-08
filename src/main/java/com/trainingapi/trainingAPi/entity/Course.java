package com.trainingapi.trainingAPi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
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
    String description;
    @Column(columnDefinition ="BOOLEAN DEFAULT TRUE")
    boolean status;

    @OneToMany(mappedBy = "courseCode", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CourseSyllabus> courseSyllabi;

    @ManyToMany
    @JoinTable(name = "prerequisite",
            joinColumns=@JoinColumn(name = "course_code",referencedColumnName = "course_code"),
            inverseJoinColumns = @JoinColumn(name = "prerequisite_id", referencedColumnName = "course_code")
            )
    Set<Course> prerequisites;

   @ManyToMany(mappedBy = "prerequisites")
    Set<Course> prerequisiteOf;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    LocalDateTime createAt;

    @UpdateTimestamp
    LocalDateTime  updateAt;


}

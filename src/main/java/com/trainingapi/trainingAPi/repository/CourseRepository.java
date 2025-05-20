package com.trainingapi.trainingAPi.repository;

import com.trainingapi.trainingAPi.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    Optional<Course> findByCourseCode(String courseCode);
    boolean existsByCourseCode(String courseCode);
    List<Course> findByCourseNameContainingIgnoreCase(String courseName);
    List<Course> findByStatus(boolean status);
    @Query("SELECT c FROM Course c WHERE LOWER(c.courseName) LIKE LOWER(CONCAT('%', :keyword, '%')) AND c.status = true")
    List<Course> searchByKeywordAndActive(@Param("keyword") String keyword);
}

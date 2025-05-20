package com.trainingapi.trainingAPi.repository;

import com.trainingapi.trainingAPi.entity.CourseSyllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseSyllabusRepository extends JpaRepository<CourseSyllabus,String> {
   List<CourseSyllabus> findByStatus(int status);

   @Query("SELECT c FROM CourseSyllabus c WHERE  LOWER(c.syllabusContent) LIKE LOWER(CONCAT('%', :keyword, '%')) AND c.status = 1")
   List<CourseSyllabus> searchCourseSyllabusByNameAndStatusIsTrue(@Param("keyword") String keyword);

}

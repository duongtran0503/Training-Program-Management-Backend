package com.trainingapi.trainingAPi.repository;

import com.trainingapi.trainingAPi.entity.CourseSyllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseSyllabusRepository extends JpaRepository<CourseSyllabus,String> {

}

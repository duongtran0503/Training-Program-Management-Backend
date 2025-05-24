package com.trainingapi.trainingAPi.repository;

import com.trainingapi.trainingAPi.entity.TrainingProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingProgramRepository extends JpaRepository<TrainingProgram,String> {
    List<TrainingProgram> findByStatus(boolean status);

    @Query("SELECT t FROM TrainingProgram t WHERE LOWER(t.trainingProgramName) LIKE LOWER(CONCAT('%',:keyword,'%')) AND t.status = true")
    List<TrainingProgram> searchTrainingProgramByNameAndStatusIsTrue(@Param("keyword") String keyword);
}
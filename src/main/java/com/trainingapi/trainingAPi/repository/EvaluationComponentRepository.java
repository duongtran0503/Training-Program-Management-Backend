package com.trainingapi.trainingAPi.repository;

import com.trainingapi.trainingAPi.entity.EvaluationComponents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationComponentRepository extends JpaRepository<EvaluationComponents,String> {
}

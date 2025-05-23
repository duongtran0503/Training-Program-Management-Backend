package com.trainingapi.trainingAPi.repository;

import com.trainingapi.trainingAPi.entity.TeachingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachingPlanRepository extends JpaRepository<TeachingPlan, String> {
}
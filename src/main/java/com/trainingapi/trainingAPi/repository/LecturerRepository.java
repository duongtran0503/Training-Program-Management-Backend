package com.trainingapi.trainingAPi.repository;

import com.trainingapi.trainingAPi.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer,String> {
}

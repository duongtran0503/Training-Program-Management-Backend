package com.trainingapi.trainingAPi.repository;

import com.trainingapi.trainingAPi.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer,String> {
    boolean existsByLecturerCode(String lecturerCode);
    List<Lecturer> findAllByName(String name);
    List<Lecturer> findByStatus(boolean status);
}

package com.trainingapi.trainingAPi.service;

import com.trainingapi.trainingAPi.dto.request.CreateLecturerRequest;
import com.trainingapi.trainingAPi.dto.request.UpdateLecturerRequest;
import com.trainingapi.trainingAPi.entity.Lecturer;
import com.trainingapi.trainingAPi.exception.AppException;
import com.trainingapi.trainingAPi.exception.ErrorCode;
import com.trainingapi.trainingAPi.repository.LecturerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LecturerService {
    LecturerRepository lecturerRepository;

    public Lecturer createLecturer(CreateLecturerRequest request) {
        if (lecturerRepository.existsByLecturerCode(request.getLecturerCode())) {
            throw new AppException(ErrorCode.LECTURER_CODE_EXISTED);
        }

        Lecturer lecturer = Lecturer.builder()
                .name(request.getName())
                .lecturerCode(request.getLecturerCode())
                .gender(request.getGender())
                .titleAcademicRank(request.getTitleAcademicRank())
                .avatar(request.getAvatar())
                .department(request.getDepartment())
                .status(request.getStatus())
                .dob(request.getDob())
                .startDateOfTeaching(request.getStartDateOfTeaching())
                .build();

        return lecturerRepository.save(lecturer);
    }

    public List<Lecturer> getAllLecturers() {
        return lecturerRepository.findAll();
    }

    public Lecturer getLecturerById(String id) {
        return lecturerRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.LECTURER_NOT_EXIST));
    }

    public Lecturer updateLecturer(String id, UpdateLecturerRequest request) {
        Lecturer lecturer = getLecturerById(id);

        if (!lecturer.getLecturerCode().equals(request.getLecturerCode()) &&
                lecturerRepository.existsByLecturerCode(request.getLecturerCode())) {
            throw new AppException(ErrorCode.LECTURER_CODE_EXISTED);
        }

        lecturer.setName(request.getName());
        lecturer.setLecturerCode(request.getLecturerCode());
        lecturer.setGender(request.getGender());
        lecturer.setStatus(request.getStatus());
        lecturer.setDob(request.getDob());
        lecturer.setStartDateOfTeaching(request.getStartDateOfTeaching());
        lecturer.setEndDateOfTeaching(request.getEndDateOfTeaching());

        return lecturerRepository.save(lecturer);
    }

    public void deleteLecturer(String id) {
        Lecturer lecturer = getLecturerById(id);
        lecturer.setStatus(false);
        lecturerRepository.save(lecturer);
    }

    public List<Lecturer> searchLecturers(String name) {
        return lecturerRepository.findByNameContainingIgnoreCase(name);
    }
} 
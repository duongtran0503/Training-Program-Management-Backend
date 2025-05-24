package com.trainingapi.trainingAPi.service;

import com.trainingapi.trainingAPi.dto.request.CreateTeachingPlanRequest;
import com.trainingapi.trainingAPi.dto.response.TeachingPlanResponse;
import com.trainingapi.trainingAPi.entity.TeachingPlan;
import com.trainingapi.trainingAPi.entity.TrainingProgram;
import com.trainingapi.trainingAPi.exception.AppException;
import com.trainingapi.trainingAPi.exception.ErrorCode;
import com.trainingapi.trainingAPi.mapper.TeachingPlanMapper;
import com.trainingapi.trainingAPi.repository.TeachingPlanRepository;
import com.trainingapi.trainingAPi.repository.TrainingProgramRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class TeachingService {
    TeachingPlanRepository teachingPlanRepository;
    TrainingProgramRepository trainingProgramRepository;
    TeachingPlanMapper teachingPlanMapper;

    public TeachingPlanResponse createTeachingPlan(CreateTeachingPlanRequest request) {
        if (teachingPlanRepository.existsById(request.getTeachingPlanId()))
            throw new AppException(ErrorCode.TEACHING_PLAN_EXISTED);

        // Kiểm tra trainingProgramId có tồn tại không
        TrainingProgram trainingProgram = trainingProgramRepository.findById(request.getTrainingProgramId())
                .orElseThrow(() -> new AppException(ErrorCode.TRAINING_PROGRAM_NOT_EXIST));

        TeachingPlan teachingPlan = teachingPlanMapper.toTeachingPlan(request);
        teachingPlan.setTrainingProgram(trainingProgram);
        return teachingPlanMapper.toTeachingPlanResponse(teachingPlanRepository.save(teachingPlan));
    }

    public List<TeachingPlanResponse> getAllTeachingPlans() {
        List<TeachingPlan> plans = teachingPlanRepository.findAll();
        log.info("Teaching plans fetched from database: {}", plans);
        return plans.stream()
                .map(teachingPlanMapper::toTeachingPlanResponse)
                .toList();
    }

    public TeachingPlanResponse getTeachingPlanById(String id) {
        return teachingPlanMapper.toTeachingPlanResponse(teachingPlanRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TEACHING_PLAN_NOT_EXIST)));
    }

    public TeachingPlanResponse updateTeachingPlan(String id, CreateTeachingPlanRequest request) {
        var teachingPlan = teachingPlanRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TEACHING_PLAN_NOT_EXIST));

        // Kiểm tra trainingProgramId
        TrainingProgram trainingProgram = trainingProgramRepository.findById(request.getTrainingProgramId())
                .orElseThrow(() -> new AppException(ErrorCode.TRAINING_PROGRAM_NOT_EXIST));

        teachingPlanMapper.updateTeachingPlan(teachingPlan, request);
        teachingPlan.setTrainingProgram(trainingProgram);
        return teachingPlanMapper.toTeachingPlanResponse(teachingPlanRepository.save(teachingPlan));
    }

    public void deleteTeachingPlan(String id) {
        var teachingPlan = teachingPlanRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TEACHING_PLAN_NOT_EXIST));
        teachingPlanRepository.delete(teachingPlan);
    }
}
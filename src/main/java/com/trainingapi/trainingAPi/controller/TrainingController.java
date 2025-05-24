package com.trainingapi.trainingAPi.controller;

import com.trainingapi.trainingAPi.dto.request.CreateTrainingProgramRequest;
import com.trainingapi.trainingAPi.dto.request.UpdateTrainingProgramRequest;
import com.trainingapi.trainingAPi.dto.response.ApiResponse;
import com.trainingapi.trainingAPi.dto.response.TrainingProgramResponse;
import com.trainingapi.trainingAPi.service.TrainingService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training-programs")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TrainingController {
    TrainingService trainingService;

    @PostMapping
    public ApiResponse<TrainingProgramResponse> createTrainingProgram(@Valid @RequestBody CreateTrainingProgramRequest request) {
        TrainingProgramResponse response = trainingService.createTrainingProgram(request);
        log.info("Created training program: {}", response);
        return ApiResponse.<TrainingProgramResponse>builder()
                .data(response)
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }

    @GetMapping
    public ApiResponse<List<TrainingProgramResponse>> getAllTrainingPrograms() {
        List<TrainingProgramResponse> programs = trainingService.getAllTrainingPrograms();
        log.info("Fetched training programs: {}", programs);
        return ApiResponse.<List<TrainingProgramResponse>>builder()
                .data(programs)
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<TrainingProgramResponse> getTrainingProgramById(@PathVariable String id) {
        return ApiResponse.<TrainingProgramResponse>builder()
                .data(trainingService.getTrainingProgramById(id))
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<TrainingProgramResponse> updateTrainingProgram(
            @PathVariable String id,
            @Valid @RequestBody UpdateTrainingProgramRequest request) {
        return ApiResponse.<TrainingProgramResponse>builder()
                .data(trainingService.updateTrainingProgram(id, request))
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteTrainingProgram(@PathVariable String id) {
        trainingService.deleteTrainingProgram(id);
        return ApiResponse.<Void>builder()
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<TrainingProgramResponse>> searchTrainingPrograms(@RequestParam String name) {
        return ApiResponse.<List<TrainingProgramResponse>>builder()
                .data(trainingService.searchTrainingPrograms(name))
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }
}
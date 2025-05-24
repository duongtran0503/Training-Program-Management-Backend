package com.trainingapi.trainingAPi.controller;

import com.trainingapi.trainingAPi.dto.request.CreateTeachingPlanRequest;
import com.trainingapi.trainingAPi.dto.response.ApiResponse;
import com.trainingapi.trainingAPi.dto.response.TeachingPlanResponse;
import com.trainingapi.trainingAPi.service.TeachingService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teaching-plans")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TeachingController {
    TeachingService teachingService;

    @PostMapping
    public ApiResponse<TeachingPlanResponse> createTeachingPlan(@Valid @RequestBody CreateTeachingPlanRequest request) {
        TeachingPlanResponse response = teachingService.createTeachingPlan(request);
        log.info("Created teaching plan: {}", response);
        return ApiResponse.<TeachingPlanResponse>builder()
                .data(response)
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }

    @GetMapping
    public ApiResponse<List<TeachingPlanResponse>> getAllTeachingPlans() {
        List<TeachingPlanResponse> plans = teachingService.getAllTeachingPlans();
        log.info("Fetched teaching plans: {}", plans);
        return ApiResponse.<List<TeachingPlanResponse>>builder()
                .data(plans)
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<TeachingPlanResponse> getTeachingPlanById(@PathVariable String id) {
        return ApiResponse.<TeachingPlanResponse>builder()
                .data(teachingService.getTeachingPlanById(id))
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<TeachingPlanResponse> updateTeachingPlan(
            @PathVariable String id,
            @Valid @RequestBody CreateTeachingPlanRequest request) {
        return ApiResponse.<TeachingPlanResponse>builder()
                .data(teachingService.updateTeachingPlan(id, request))
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteTeachingPlan(@PathVariable String id) {
        teachingService.deleteTeachingPlan(id);
        return ApiResponse.<Void>builder()
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }
}
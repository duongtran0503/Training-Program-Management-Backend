package com.trainingapi.trainingAPi.controller;

import com.trainingapi.trainingAPi.dto.request.CreateTeachingPlanRequest;
import com.trainingapi.trainingAPi.dto.response.ApiResponse;
import com.trainingapi.trainingAPi.dto.response.TeachingPlanResponse;
import com.trainingapi.trainingAPi.service.TeachingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teaches")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class TeachingController {
    TeachingService teachingService;

    @PostMapping
    public ApiResponse<TeachingPlanResponse> createTeachingPlan(@RequestBody CreateTeachingPlanRequest request) {
        return ApiResponse.<TeachingPlanResponse>builder()
                .isSuccess(true)
                .statusCode(200)
                .data(teachingService.createTeachingPlan(request))
                .build();
    }

    @GetMapping
    public  ApiResponse<List<TeachingPlanResponse>> getAllTeachingPlan() {
        return  ApiResponse.<List<TeachingPlanResponse>>builder()
                .isSuccess(true)
                .statusCode(200)
                .data(teachingService.getAllTeachingPlan())
                .build();
    }
}

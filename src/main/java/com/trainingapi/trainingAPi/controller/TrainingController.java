package com.trainingapi.trainingAPi.controller;

import com.trainingapi.trainingAPi.dto.request.CreateKnowledgeBlockRequest;
import com.trainingapi.trainingAPi.dto.request.CreateTrainingProgramRequest;
import com.trainingapi.trainingAPi.dto.request.UpdateTrainingProgramRequest;
import com.trainingapi.trainingAPi.dto.response.ApiResponse;
import com.trainingapi.trainingAPi.dto.response.KnowledgeBlockResponse;
import com.trainingapi.trainingAPi.dto.response.TrainingProgramResponse;
import com.trainingapi.trainingAPi.service.TrainingService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/trainings")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class TrainingController {
    TrainingService trainingService;

    @PostMapping
    public ApiResponse<TrainingProgramResponse> createTrainingProgram(@Valid @RequestBody CreateTrainingProgramRequest request) {
     return   ApiResponse.<TrainingProgramResponse>builder()
             .isSuccess(true)
             .statusCode(200)
             .data(trainingService.createTrainingProgram(request))
             .build();
    }

    @GetMapping
    public  ApiResponse<List<TrainingProgramResponse>> getAllTrainingProgram() {
        return ApiResponse.<List<TrainingProgramResponse>>builder()
                .isSuccess(true)
                .statusCode(200)
                .data(trainingService.getAllTrainingProgram())
                .build();
    }

    @GetMapping("{id}")
    public  ApiResponse<TrainingProgramResponse> getTrainingProgramById(@PathVariable String id) {
        return  ApiResponse.<TrainingProgramResponse>builder()
                .statusCode(200)
                .isSuccess(true)
                .data(trainingService.getTrainingProgramById(id))
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<TrainingProgramResponse>> getTrainingProgramByName(
            @RequestParam(name = "name",defaultValue = "",required = false) String name
    ) {
        return  ApiResponse.<List<TrainingProgramResponse>>builder()
                .isSuccess(true)
                .statusCode(200)
                .data(trainingService.getTrainingProgramByName(name))
                .build();
    }

    @PutMapping
    public  ApiResponse<TrainingProgramResponse> updateTrainingProgram(@Valid @RequestBody UpdateTrainingProgramRequest request) {
        return  ApiResponse.<TrainingProgramResponse>builder()
                .isSuccess(true)
                .statusCode(200)
                .data(trainingService.updateTrainingProgram(request))
                .build();
    }

    @DeleteMapping("/{id}")
    public  ApiResponse<Void> deleteTrainingProgram(@PathVariable String id) {
         trainingService.deleteTrainingProgram(id);
         return  ApiResponse.<Void>builder()
                 .statusCode(200)
                 .isSuccess(true)
                 .message("OK")
                 .build();
    }

   @PostMapping("/knowledge-block")
    public ApiResponse<KnowledgeBlockResponse> craateKnowLedgeBlock(@Valid @RequestBody CreateKnowledgeBlockRequest request) {
        return  ApiResponse.<KnowledgeBlockResponse>builder()
                .isSuccess(true)
                .statusCode(200)
                .data(trainingService.createKnowledgeBlock(request))
                .build();
   }

   @DeleteMapping("/knowledge/{id}")
    public ApiResponse<Void> deleteKnowledgeBlock(@PathVariable String id) {

        trainingService.deleteKnowledgeBlock(id);
        return ApiResponse.<Void>builder()
                .isSuccess(true)
                .message("OK")
                .statusCode(200)
                .build();
   }

}

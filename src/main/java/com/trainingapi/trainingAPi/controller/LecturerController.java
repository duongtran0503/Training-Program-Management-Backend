package com.trainingapi.trainingAPi.controller;

import com.trainingapi.trainingAPi.dto.request.CreateLecturerRequest;
import com.trainingapi.trainingAPi.dto.request.UpdateLecturerRequest;
import com.trainingapi.trainingAPi.dto.response.ApiResponse;
import com.trainingapi.trainingAPi.entity.Lecturer;
import com.trainingapi.trainingAPi.service.LecturerService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/lecturer")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LecturerController {
    LecturerService lecturerService;

    @PostMapping
    public ApiResponse<Lecturer> createLecturer(@Valid @RequestBody CreateLecturerRequest request) {
        return ApiResponse.<Lecturer>builder()
                .data(lecturerService.createLecturer(request))
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }

    @GetMapping("/get-all")
    public ApiResponse<List<Lecturer>> getAllLecturers() {
        return ApiResponse.<List<Lecturer>>builder()
                .data(lecturerService.getAllLecturers())
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<Lecturer> getLecturerById(@PathVariable String id) {
        return ApiResponse.<Lecturer>builder()
                .data(lecturerService.getLecturerById(id))
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<Lecturer> updateLecturer(
            @PathVariable String id,
            @Valid @RequestBody UpdateLecturerRequest request) {
        return ApiResponse.<Lecturer>builder()
                .data(lecturerService.updateLecturer(id, request))
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteLecturer(@PathVariable String id) {
        lecturerService.deleteLecturer(id);
        return ApiResponse.<Void>builder()
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<Lecturer>> searchLecturers(@RequestParam String name) {
        return ApiResponse.<List<Lecturer>>builder()
                .data(lecturerService.searchLecturers(name))
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }
} 
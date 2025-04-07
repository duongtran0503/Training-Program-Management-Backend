package com.trainingapi.trainingAPi.controller;

import com.trainingapi.trainingAPi.dto.request.CreateCourseRequest;
import com.trainingapi.trainingAPi.dto.request.UpdateCourseRequest;
import com.trainingapi.trainingAPi.dto.response.ApiResponse;
import com.trainingapi.trainingAPi.dto.response.CourseResponse;
import com.trainingapi.trainingAPi.service.CourseService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class CourseController {
    CourseService courseService;

    @PostMapping
    public ApiResponse<CourseResponse> createCourse(@RequestBody @Valid CreateCourseRequest request) {
        return  ApiResponse.<CourseResponse>builder()
                .isSuccess(true)
                .statusCode(200)
                .data(courseService.CreateCourse(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<CourseResponse>> getAllCourse() {
        return  ApiResponse.<List<CourseResponse>>builder()
                .isSuccess(true)
                .statusCode(200)
                .data(courseService.getAllCourse())
                .build();
    }

    @DeleteMapping("/{id}")
    public  ApiResponse<Void> deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
        return ApiResponse.<Void>builder().isSuccess(true).statusCode(200).message("OK").build();
    }

    @PutMapping("/{id}")
    public ApiResponse<CourseResponse> updateCourse(@Valid @RequestBody UpdateCourseRequest request
            ,@PathVariable String id) {
        return  ApiResponse.<CourseResponse>builder()
                .statusCode(200)
                .isSuccess(true)
                .data(courseService.updateCourse(request,id))
                .build();
    }
}

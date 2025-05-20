package com.trainingapi.trainingAPi.controller;

import com.trainingapi.trainingAPi.dto.request.*;
import com.trainingapi.trainingAPi.dto.response.ApiResponse;
import com.trainingapi.trainingAPi.dto.response.CourseResponse;
import com.trainingapi.trainingAPi.dto.response.CourseSyllabusResponse;
import com.trainingapi.trainingAPi.entity.Course;
import com.trainingapi.trainingAPi.service.CourseService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class CourseController {
    CourseService courseService;

    @PostMapping
    public ApiResponse<Course> createCourse(@Valid @RequestBody CreateCourseRequest request) {
        return ApiResponse.<Course>builder()
                .data(courseService.createCourse(request))
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }

    @GetMapping
    public ApiResponse<List<Course>> getAllCourses() {
        return ApiResponse.<List<Course>>builder()
                .data(courseService.getAllCourses())
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }

    @GetMapping("/{courseCode}")
    public ApiResponse<Course> getCourseByCode(@PathVariable String courseCode) {
        return ApiResponse.<Course>builder()
                .data(courseService.getCourseByCode(courseCode))
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<Course>> searchCourses(@RequestParam String name) {
        return ApiResponse.<List<Course>>builder()
                .data(courseService.searchCourses(name))
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }

    @DeleteMapping("/{courseCode}")
    public ApiResponse<Void> deleteCourse(@PathVariable String courseCode) {
        courseService.deleteCourse(courseCode);
        return ApiResponse.<Void>builder()
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }

    @PutMapping("/{courseCode}")
    public ApiResponse<Course> updateCourse(
            @PathVariable String courseCode,
            @Valid @RequestBody UpdateCourseRequest request) {
        return ApiResponse.<Course>builder()
                .data(courseService.updateCourse(courseCode, request))
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }

    @PostMapping("/course-syllabus/{id}")
    public ApiResponse<CourseSyllabusResponse> addCourseSyllabus(@Valid @RequestBody CreateCourseSyllabusRequest request
            , @PathVariable String id) {
        return ApiResponse.<CourseSyllabusResponse>builder()
                .isSuccess(true)
                .statusCode(200)
                .data(courseService.createCourseSyllabus(request, id))
                .build();
    }

    @GetMapping("/course-syllabus")
    public ApiResponse<List<CourseSyllabusResponse>> getAllCourseSyllabus() {
         return  ApiResponse.<List<CourseSyllabusResponse>>builder()
                 .isSuccess(true)
                 .statusCode(200)
                 .data(courseService.getAllCourseSyllabus())
                 .build();
    }

    @GetMapping("/course-syllabus/{id}")
    public  ApiResponse<CourseSyllabusResponse> getCourseSyllabusById(@PathVariable String id) {
        return  ApiResponse.<CourseSyllabusResponse>builder()
                .isSuccess(true)
                .statusCode(200)
                .data(courseService.getCourseSyllabusById(id))
                .build();
    }

    @GetMapping("/course-syllabus/search")
    public  ApiResponse<List<CourseSyllabusResponse>> getCourseSyllabusByName(
            @RequestParam(name = "name",required = false,defaultValue = "") String name) {
        return  ApiResponse.<List<CourseSyllabusResponse>>builder()
                .isSuccess(true)
                .statusCode(200)
                .data(courseService.getCourseSyllabusByName(name))
                .build();
    }

    @PutMapping("/course-syllabus/{id}")
    public ApiResponse<CourseSyllabusResponse> updateCourseSyllabus(@Valid @RequestBody UpdateCourseSyllabusRequest request
    ,@PathVariable String id) {
        return  ApiResponse.<CourseSyllabusResponse>builder()
                .isSuccess(true)
                .statusCode(200)
                .data(courseService.updateCourseSyllabus(request,id))
                .build();
    }

    @DeleteMapping("/course-syllabus/{id}")
    public ApiResponse<Void> deleteCourseSyllabus(@PathVariable String id) {
        courseService.deleteCourseSyllabus(id);
        return  ApiResponse.<Void>builder()
                .isSuccess(true)
                .message("OK")
                .statusCode(200)
                .build();
    }

    @PostMapping("/prerequisites/{courseCode}")
    public ApiResponse<Course> addPrerequisites(
            @PathVariable String courseCode,
            @RequestBody List<String> prerequisiteCodes) {
        return ApiResponse.<Course>builder()
                .data(courseService.addPrerequisites(courseCode, prerequisiteCodes))
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }

    @DeleteMapping("/prerequisites/{courseCode}/{prerequisiteCode}")
    public ApiResponse<Course> removePrerequisite(
            @PathVariable String courseCode,
            @PathVariable String prerequisiteCode) {
        return ApiResponse.<Course>builder()
                .data(courseService.removePrerequisite(courseCode, prerequisiteCode))
                .statusCode(200)
                .isSuccess(true)
                .message("OK")
                .build();
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<Void> importCourses(@RequestParam("file") MultipartFile file) {
        courseService.importCourses(file);
        return ApiResponse.<Void>builder()
                .statusCode(200)
                .isSuccess(true)
                .message("Import thành công")
                .build();
    }
}

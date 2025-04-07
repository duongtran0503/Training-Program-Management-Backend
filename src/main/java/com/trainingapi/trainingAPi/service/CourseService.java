package com.trainingapi.trainingAPi.service;

import com.trainingapi.trainingAPi.dto.request.CreateCourseRequest;
import com.trainingapi.trainingAPi.dto.request.UpdateCourseRequest;
import com.trainingapi.trainingAPi.dto.response.CourseResponse;
import com.trainingapi.trainingAPi.entity.Course;
import com.trainingapi.trainingAPi.exception.AppException;
import com.trainingapi.trainingAPi.exception.ErrorCode;
import com.trainingapi.trainingAPi.mapper.CourseMapper;
import com.trainingapi.trainingAPi.repository.CourseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class CourseService {
   CourseRepository courseRepository;
   CourseMapper courseMapper;

   public CourseResponse CreateCourse(CreateCourseRequest request) {
      if(courseRepository.existsById(request.getCourseCode()))
          throw  new AppException(ErrorCode.COURSE_EXISTED);
       Course course = courseMapper.toCourse(request);
       return courseMapper.toCourseResponse(courseRepository.save(course));
   }

   public List<CourseResponse> getAllCourse() {
       return  courseRepository.findAll().stream().map(courseMapper::toCourseResponse).toList();
   }

   public  void deleteCourse(String courseCode) {
      var course = courseRepository.findById(courseCode).orElseThrow(()->new AppException(ErrorCode.COURSE_NOT_EXIST));

      course.setStatus(false);
      courseRepository.save(course);

   }

   public CourseResponse updateCourse(UpdateCourseRequest request,String courseCode) {
       var course = courseRepository.findById(courseCode).orElseThrow(()->new AppException(ErrorCode.COURSE_NOT_EXIST));

       courseMapper.updateCourse(course,request);

     return courseMapper.toCourseResponse(courseRepository.save(course));
   }
}

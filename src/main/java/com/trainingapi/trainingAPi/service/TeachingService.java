package com.trainingapi.trainingAPi.service;

import com.trainingapi.trainingAPi.dto.request.CreateTeachingPlanRequest;
import com.trainingapi.trainingAPi.dto.response.TeachingPlanResponse;
import com.trainingapi.trainingAPi.entity.Course;
import com.trainingapi.trainingAPi.entity.TeachingPlan;
import com.trainingapi.trainingAPi.exception.AppException;
import com.trainingapi.trainingAPi.exception.ErrorCode;
import com.trainingapi.trainingAPi.mapper.TeachingPlanMapper;
import com.trainingapi.trainingAPi.repository.CourseRepository;
import com.trainingapi.trainingAPi.repository.TeachingPlanRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class TeachingService {
     TeachingPlanRepository teachingPlanRepository;
     TeachingPlanMapper teachingPlanMapper;
     CourseRepository courseRepository;

     public TeachingPlanResponse createTeachingPlan(CreateTeachingPlanRequest request) {
        if(teachingPlanRepository.existsById(request.getTeachingPlanId()))
            throw  new AppException(ErrorCode.TEACHING_PLAN_EXISTED);
         TeachingPlan teachingPlan = teachingPlanMapper.toTeachingPlan(request);
         List<Course> courses = new ArrayList<>();
         if(request.getCourseId()!=null & !request.getCourseId().isEmpty()) {
            request.getCourseId().forEach(courseCode->{
                var course =  courseRepository.findById(courseCode).orElseThrow(
                        ()->new AppException(ErrorCode.TEACHING_PLAN_NOT_EXIST)
                );
                courses.add(course);
            });

         }
         teachingPlan.setCourses(courses);
         return  teachingPlanMapper.toTeachingPlanResponse(teachingPlanRepository.save(teachingPlan));
     }
}

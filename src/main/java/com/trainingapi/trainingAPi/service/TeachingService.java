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
import com.trainingapi.trainingAPi.repository.TrainingProgramRepository;
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
     TrainingProgramRepository trainingProgramRepository;
     CourseRepository courseRepository;

     public TeachingPlanResponse createTeachingPlan(CreateTeachingPlanRequest request) {
        if(teachingPlanRepository.existsById(request.getTeachingPlanId()))
            throw  new AppException(ErrorCode.TEACHING_PLAN_EXISTED);
        var trainingProgram = trainingProgramRepository.findById(request.getTrainingProgramId())
                .orElseThrow(()->new AppException(ErrorCode.TRAINING_PROGRAM_NOT_EXIST));
        TeachingPlan teachingPlan = teachingPlanMapper.toTeachingPlan(request);
         teachingPlan.setTrainingProgram(trainingProgram);
         List<Course> courses = new ArrayList<>();
         if(request.getCourseId()!=null & !request.getCourseId().isEmpty()) {
            request.getCourseId().forEach(courseCode->{
                var course =  courseRepository.findById(courseCode).orElseThrow(
                        ()->new AppException(ErrorCode.TEACHING_PLAN_NOT_EXIST)
                );
                course.setTeachingPlan(teachingPlan);
                courses.add(course);
            });

         }
         teachingPlan.setCourses(courses);
         return  teachingPlanMapper.toTeachingPlanResponse(teachingPlanRepository.save(teachingPlan));
     }

     public  List<TeachingPlanResponse> getAllTeachingPlan() {
         return  teachingPlanRepository.findAll().stream().map(teachingPlanMapper::toTeachingPlanResponse).toList();
     }
}

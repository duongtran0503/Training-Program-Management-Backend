package com.trainingapi.trainingAPi.service;

import com.trainingapi.trainingAPi.dto.request.*;
import com.trainingapi.trainingAPi.dto.response.CourseResponse;
import com.trainingapi.trainingAPi.dto.response.CourseSyllabusResponse;
import com.trainingapi.trainingAPi.entity.Course;
import com.trainingapi.trainingAPi.entity.CourseSyllabus;
import com.trainingapi.trainingAPi.exception.AppException;
import com.trainingapi.trainingAPi.exception.ErrorCode;
import com.trainingapi.trainingAPi.mapper.CourseMapper;
import com.trainingapi.trainingAPi.mapper.CourseSyllabusMapper;
import com.trainingapi.trainingAPi.repository.CourseRepository;
import com.trainingapi.trainingAPi.repository.CourseSyllabusRepository;
import com.trainingapi.trainingAPi.repository.EvaluationComponentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class  CourseService {
   CourseRepository courseRepository;
   CourseMapper courseMapper;
   CourseSyllabusRepository courseSyllabusRepository;
   EvaluationComponentRepository evaluationComponentRepository;
   CourseSyllabusMapper courseSyllabusMapper;

   public CourseResponse CreateCourse(CreateCourseRequest request) {
      if(courseRepository.existsById(request.getCourseCode()))
          throw  new AppException(ErrorCode.COURSE_EXISTED);
       HashSet<Course> pprerequisites = new HashSet<>();
       Course course = courseMapper.toCourse(request);
       if(request.getPrerequisites() !=null & !request.getPrerequisites().isEmpty()) {
         for(String courseCode: request.getPrerequisites()) {
            var coure = courseRepository.findById(courseCode).orElseThrow(
                    ()->new AppException(ErrorCode.COURSE_NOT_EXIST)
            );
            pprerequisites.add(coure);
         }
       }
       course.setPrerequisites(pprerequisites);
       return courseMapper.toCourseResponse(courseRepository.save(course));
   }

   public List<CourseResponse> getAllCourse() {
       return  courseRepository.findAll().stream().map(courseMapper::toCourseResponse).toList();
   }

   public  CourseResponse getCourseByCourseCode(String courseCode) {
        return  courseMapper.toCourseResponse(courseRepository.findById(courseCode)
                .orElseThrow(()->new AppException(ErrorCode.COURSE_NOT_EXIST)));
   }

   public List<CourseResponse> getCourseByName(String name) {
       return courseRepository.searchByKeywordAndActive(name).stream().map(
               courseMapper::toCourseResponse
       ).toList();
   }

   public  void deleteCourse(String courseCode) {
      var course = courseRepository.findById(courseCode).orElseThrow(
              ()->new AppException(ErrorCode.COURSE_NOT_EXIST));
      course.setStatus(false);
      courseRepository.save(course);

   }

   public CourseResponse updateCourse(UpdateCourseRequest request,String courseCode) {
       var course = courseRepository.findById(courseCode).orElseThrow(()->new AppException(ErrorCode.COURSE_NOT_EXIST));

       courseMapper.updateCourse(course,request);

     return courseMapper.toCourseResponse(courseRepository.save(course));
   }

   @Transactional
   public CourseSyllabusResponse createCourseSyllabus(CreateCourseSyllabusRequest request, String courseCode) {
       var course = courseRepository.findById(courseCode).orElseThrow(()->new AppException(ErrorCode.COURSE_NOT_EXIST));
       CourseSyllabus courseSyllabus = courseSyllabusMapper.toCourseSyllabus(request);
       courseSyllabus.setCourseCode(course);
       courseSyllabus.setSyllabusId(UUID.randomUUID().toString());
       if(courseSyllabus.getEvaluationComponents()!=null) {

           courseSyllabus.getEvaluationComponents().setCourseSyllabus(courseSyllabus);

       }
       return  courseSyllabusMapper.toCourseSyllabusResponse(courseSyllabusRepository.save(courseSyllabus));
   }

   public List<CourseSyllabusResponse> getAllCourseSyllabus() {
       return  courseSyllabusRepository.findAll().stream()
               .map(courseSyllabusMapper::toCourseSyllabusResponse).toList();
   }

   public  CourseSyllabusResponse getCourseSyllabusById(String id) {
       return  courseSyllabusMapper.toCourseSyllabusResponse(courseSyllabusRepository.findById(id)
               .orElseThrow(()->new AppException(ErrorCode.COURSE_SYLLABUS_NOT_EXIST))
       );
   }

   public  List<CourseSyllabusResponse> getCourseSyllabusByName(String name) {
       return  courseSyllabusRepository.searchCourseSyllabusByNameAndStatusIsTrue(name).stream()
               .map(courseSyllabusMapper::toCourseSyllabusResponse).toList();
   }

   public  CourseSyllabusResponse updateCourseSyllabus(UpdateCourseSyllabusRequest request,String id) {
      var courseSyllabus = courseSyllabusRepository. findById(id).orElseThrow(
              ()-> new AppException(ErrorCode.COURSE_SYLLABUS_NOT_EXIST));
       courseSyllabusMapper.toCourseSyllabusUpdate(courseSyllabus,request);
      return  courseSyllabusMapper.toCourseSyllabusResponse(courseSyllabusRepository.save(courseSyllabus));
   }

   public  void deleteCourseSyllabus(String id) {
       var courseSyllabus = courseSyllabusRepository.findById(id).orElseThrow(
               ()->new AppException(ErrorCode.COURSE_SYLLABUS_NOT_EXIST)
       );

       courseSyllabus.setStatus(false);
       courseSyllabusRepository.save(courseSyllabus);
   }

   public CourseResponse addPrerequisites(String courseCode, AddPrerequisitesRequest request) {
       var course = courseRepository.findById(courseCode).orElseThrow(
               ()->new AppException(ErrorCode.COURSE_NOT_EXIST)
       );

       Set<Course> currentPrerequisite = course.getPrerequisites();
       Set<Course> newPrerequisite = new HashSet<>();

       if(request.getPrerequisites()!=null & !request.getPrerequisites().isEmpty()) {
          for(String prerequisite :request.getPrerequisites()) {
              if(currentPrerequisite.stream().noneMatch(p->p.getCourseCode().equals(prerequisite))) {
               Course prerequisiteCourse  = courseRepository.findById(prerequisite).orElseThrow(
                       ()->new AppException(ErrorCode.COURSE_NOT_EXIST)
               );
               newPrerequisite.add(prerequisiteCourse );
              }
          }

       }
       currentPrerequisite.addAll(newPrerequisite);
       course.setPrerequisites(currentPrerequisite);
       return  courseMapper.toCourseResponse(courseRepository.save(course));
   }

    public CourseResponse deletePrerequisite(String courseCode, DeletePrerequisitesRequest request) {
        var course = courseRepository.findById(courseCode).orElseThrow(
                ()->new AppException(ErrorCode.COURSE_NOT_EXIST)
        );

        Set<Course> currentPrerequisite = course.getPrerequisites();
        Set<Course> prerequisitesToKeep = new HashSet<>();
        for (Course prerequisite : currentPrerequisite) {
            if (!prerequisite.getCourseCode().equals(request.getPrerequisiteCode())) {
                prerequisitesToKeep.add(prerequisite);
            }
        }
        course.setPrerequisites(prerequisitesToKeep);
        return  courseMapper.toCourseResponse(courseRepository.save(course));
    }

}

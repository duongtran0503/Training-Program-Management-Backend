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
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class CourseService {
   CourseRepository courseRepository;
   CourseMapper courseMapper;
   CourseSyllabusRepository courseSyllabusRepository;
   EvaluationComponentRepository evaluationComponentRepository;
   CourseSyllabusMapper courseSyllabusMapper;

   @Transactional
   public Course createCourse(CreateCourseRequest request) {
      if (courseRepository.existsByCourseCode(request.getCourseCode())) {
          throw new AppException(ErrorCode.COURSE_EXISTED);
      }

      Course course = Course.builder()
              .courseCode(request.getCourseCode())
              .courseName(request.getCourseName())
              .credits(request.getCredits())
              .description(request.getDescription())
              .status(request.getStatus())
              .build();

      if (request.getPrerequisites() != null && !request.getPrerequisites().isEmpty()) {
          Set<Course> prerequisites = request.getPrerequisites().stream()
                  .map(code -> courseRepository.findByCourseCode(code)
                          .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_EXIST)))
                  .collect(Collectors.toSet());
          course.setPrerequisites(prerequisites);
      }

      return courseRepository.save(course);
   }

   public List<Course> getAllCourses() {
       return courseRepository.findAll();
   }

   public Course getCourseByCode(String courseCode) {
       return courseRepository.findByCourseCode(courseCode)
               .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_EXIST));
   }

   @Transactional
   public Course updateCourse(String courseCode, UpdateCourseRequest request) {
       Course course = getCourseByCode(courseCode);

       if (request.getCourseName() != null) {
           course.setCourseName(request.getCourseName());
       }
       if (request.getCredits() != null) {
           course.setCredits(request.getCredits());
       }
       if (request.getDescription() != null) {
           course.setDescription(request.getDescription());
       }
       if (request.getStatus() != null) {
           course.setStatus(request.getStatus());
       }

       return courseRepository.save(course);
   }

   public void deleteCourse(String courseCode) {
       Course course = getCourseByCode(courseCode);
       course.setStatus(false);
       courseRepository.save(course);
   }

   public List<Course> searchCourses(String name) {
       return courseRepository.findByCourseNameContainingIgnoreCase(name);
   }

   @Transactional
   public Course addPrerequisites(String courseCode, List<String> prerequisiteCodes) {
       Course course = getCourseByCode(courseCode);
       
       Set<Course> prerequisites = prerequisiteCodes.stream()
               .map(code -> courseRepository.findByCourseCode(code)
                       .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_EXIST)))
               .collect(Collectors.toSet());
       
       course.getPrerequisites().addAll(prerequisites);
       return courseRepository.save(course);
   }

   @Transactional
   public Course removePrerequisite(String courseCode, String prerequisiteCode) {
       Course course = getCourseByCode(courseCode);
       Course prerequisite = getCourseByCode(prerequisiteCode);
       
       course.getPrerequisites().remove(prerequisite);
       return courseRepository.save(course);
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

   @Transactional
   public CourseSyllabusResponse createCourseSyllabus(CreateCourseSyllabusRequest request, String courseCode) {
       var course = courseRepository.findByCourseCode(courseCode)
               .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_EXIST));
       CourseSyllabus courseSyllabus = courseSyllabusMapper.toCourseSyllabus(request);
       courseSyllabus.setCourseCode(course);
       courseSyllabus.setSyllabusId(UUID.randomUUID().toString());
       courseSyllabus.setStatus(1);
       if(courseSyllabus.getEvaluationComponents()!=null) {
           courseSyllabus.getEvaluationComponents().setCourseSyllabus(courseSyllabus);
       }
       return courseSyllabusMapper.toCourseSyllabusResponse(courseSyllabusRepository.save(courseSyllabus));
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

   public void deleteCourseSyllabus(String id) {
       var courseSyllabus = courseSyllabusRepository.findById(id).orElseThrow(
               ()->new AppException(ErrorCode.COURSE_SYLLABUS_NOT_EXIST)
       );

       courseSyllabus.setStatus(0);
       courseSyllabusRepository.save(courseSyllabus);
   }

   @Transactional
   public void importCourses(MultipartFile file) {
       try {
           Workbook workbook = WorkbookFactory.create(file.getInputStream());
           Sheet sheet = workbook.getSheetAt(0);

           List<Course> courses = new ArrayList<>();

           // Skip header row
           for (int i = 1; i <= sheet.getLastRowNum(); i++) {
               Row row = sheet.getRow(i);
               if (row == null) continue;

               String courseCode = getCellValueAsString(row.getCell(0));
               String courseName = getCellValueAsString(row.getCell(1));
               Integer credits = getCellValueAsInteger(row.getCell(2));
               Integer theoryHours = getCellValueAsInteger(row.getCell(3));
               Integer practiceHours = getCellValueAsInteger(row.getCell(4));
               Integer internshipHours = getCellValueAsInteger(row.getCell(5));
               String department = getCellValueAsString(row.getCell(6));

               if (courseCode == null || courseName == null || credits == null) {
                   continue; // Skip invalid rows
               }

               String description = String.format("%d tiết lý thuyết, %d tiết thực hành, %d tiết thực tập, Khoa/Bộ môn: %s",
                       theoryHours != null ? theoryHours : 0,
                       practiceHours != null ? practiceHours : 0,
                       internshipHours != null ? internshipHours : 0,
                       department != null ? department : "CNTT");

               Course course = Course.builder()
                       .courseCode(courseCode)
                       .courseName(courseName)
                       .credits(credits)
                       .description(description)
                       .status(true)
                       .build();

               courses.add(course);
           }

           // Save all courses
           courseRepository.saveAll(courses);

           workbook.close();
       } catch (IOException e) {
           log.error("Error importing courses from Excel", e);
           throw new AppException(ErrorCode.INVALID_FILE);
       }
   }

   private String getCellValueAsString(Cell cell) {
       if (cell == null) return null;
       switch (cell.getCellType()) {
           case STRING:
               return cell.getStringCellValue();
           case NUMERIC:
               return String.valueOf((int) cell.getNumericCellValue());
           default:
               return null;
       }
   }

   private Integer getCellValueAsInteger(Cell cell) {
       if (cell == null) return null;
       switch (cell.getCellType()) {
           case NUMERIC:
               return (int) cell.getNumericCellValue();
           case STRING:
               try {
                   return Integer.parseInt(cell.getStringCellValue());
               } catch (NumberFormatException e) {
                   return null;
               }
           default:
               return null;
       }
   }
}

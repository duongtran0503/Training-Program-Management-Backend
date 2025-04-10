package com.trainingapi.trainingAPi.service;

import com.trainingapi.trainingAPi.dto.request.CreateTrainingProgramRequest;
import com.trainingapi.trainingAPi.dto.request.UpdateTrainingProgramRequest;
import com.trainingapi.trainingAPi.dto.response.TrainingProgramResponse;
import com.trainingapi.trainingAPi.entity.TrainingProgram;
import com.trainingapi.trainingAPi.exception.AppException;
import com.trainingapi.trainingAPi.exception.ErrorCode;
import com.trainingapi.trainingAPi.mapper.TrainingProgramMapper;
import com.trainingapi.trainingAPi.repository.TrainingProgramRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class TrainingService {
   TrainingProgramRepository trainingProgramRepository;
   TrainingProgramMapper trainingProgramMapper;

   public TrainingProgramResponse createTrainingProgram(CreateTrainingProgramRequest request) {
       if(trainingProgramRepository.existsById(request.getTrainingProgramId()))
              throw  new AppException(ErrorCode.TRAINING_PROGRAM_EXISTED);
       TrainingProgram trainingProgram = trainingProgramMapper.toTrainingProgram(request);
       return  trainingProgramMapper.toTrainingProgramResponse(trainingProgramRepository.save(trainingProgram));
   }

   public List<TrainingProgramResponse> getAllTrainingProgram() {
       return trainingProgramRepository.findByStatus(true).stream()
               .map(trainingProgramMapper::toTrainingProgramResponse).toList();
   }

   public TrainingProgramResponse getTrainingProgramById(String id) {
       return trainingProgramMapper.toTrainingProgramResponse(trainingProgramRepository.findById(id)
               .orElseThrow(()->new AppException(ErrorCode.TRAINING_PROGRAM_NOT_EXIST)));
   }

   public  List<TrainingProgramResponse> getTrainingProgramByName(String name) {
     return  trainingProgramRepository.searchTrainingProgramByNameAndStatusIsTrue(name).stream()
             .map(trainingProgramMapper::toTrainingProgramResponse).toList();
   }

   public TrainingProgramResponse updateTrainingProgram(UpdateTrainingProgramRequest request) {
       var trainingProgram = trainingProgramRepository.findById(request.getTrainingProgramId())
               .orElseThrow(()->new AppException(ErrorCode.TRAINING_PROGRAM_NOT_EXIST));

       trainingProgramMapper.updateTrainingProgram(trainingProgram,request);
       return trainingProgramMapper.toTrainingProgramResponse(trainingProgramRepository.save(trainingProgram));
   }

   public void deleteTrainingProgram(String id) {
     var trainingProgram = trainingProgramRepository.findById(id)
             .orElseThrow(()->new AppException(ErrorCode.TRAINING_PROGRAM_NOT_EXIST));
      trainingProgram.setStatus(false);
      trainingProgramRepository.save(trainingProgram);
   }




}

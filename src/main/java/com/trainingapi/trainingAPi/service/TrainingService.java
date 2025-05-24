package com.trainingapi.trainingAPi.service;

import com.trainingapi.trainingAPi.dto.request.CreateKnowledgeBlockRequest;
import com.trainingapi.trainingAPi.dto.request.CreateTrainingProgramRequest;
import com.trainingapi.trainingAPi.dto.request.UpdateTrainingProgramRequest;
import com.trainingapi.trainingAPi.dto.response.KnowledgeBlockResponse;
import com.trainingapi.trainingAPi.dto.response.TrainingProgramResponse;
import com.trainingapi.trainingAPi.entity.KnowledgeBlock;
import com.trainingapi.trainingAPi.entity.KnowledgeBlockDetail;
import com.trainingapi.trainingAPi.entity.TrainingProgram;
import com.trainingapi.trainingAPi.exception.AppException;
import com.trainingapi.trainingAPi.exception.ErrorCode;
import com.trainingapi.trainingAPi.mapper.TrainingProgramMapper;
import com.trainingapi.trainingAPi.repository.KnowledgeBlockDetailRepository;
import com.trainingapi.trainingAPi.repository.KnowledgeBlockRepository;
import com.trainingapi.trainingAPi.repository.TrainingProgramRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class TrainingService {
    TrainingProgramRepository trainingProgramRepository;
    TrainingProgramMapper trainingProgramMapper;
    KnowledgeBlockRepository knowledgeBlockRepository;
    KnowledgeBlockDetailRepository knowledgeBlockDetailRepository;

    public TrainingProgramResponse createTrainingProgram(CreateTrainingProgramRequest request) {
        if (trainingProgramRepository.existsById(request.getTrainingProgramId()))
            throw new AppException(ErrorCode.TRAINING_PROGRAM_EXISTED);
        TrainingProgram trainingProgram = trainingProgramMapper.toTrainingProgram(request);
        return trainingProgramMapper.toTrainingProgramResponse(trainingProgramRepository.save(trainingProgram));
    }

    public List<TrainingProgramResponse> getAllTrainingPrograms() {
        try {
            log.info("Fetching training programs with status=true");
            List<TrainingProgram> programs = trainingProgramRepository.findByStatus(true);
            log.info("Training programs fetched from database: {}", programs);

            log.info("Mapping training programs to response DTOs");
            List<TrainingProgramResponse> responses = programs.stream()
                    .map(program -> {
                        try {
                            return trainingProgramMapper.toTrainingProgramResponse(program);
                        } catch (Exception e) {
                            log.error("Error mapping training program {}: {}", program.getTrainingProgramId(), e.getMessage(), e);
                            throw e;
                        }
                    })
                    .toList();
            log.info("Mapped training programs to responses: {}", responses);

            return responses;
        } catch (Exception e) {
            log.error("Error fetching training programs: {}", e.getMessage(), e);
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    public TrainingProgramResponse getTrainingProgramById(String id) {
        return trainingProgramMapper.toTrainingProgramResponse(trainingProgramRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TRAINING_PROGRAM_NOT_EXIST)));
    }

    public List<TrainingProgramResponse> searchTrainingPrograms(String name) {
        return trainingProgramRepository.searchTrainingProgramByNameAndStatusIsTrue(name).stream()
                .map(trainingProgramMapper::toTrainingProgramResponse)
                .toList();
    }

    public TrainingProgramResponse updateTrainingProgram(String id, UpdateTrainingProgramRequest request) {
        var trainingProgram = trainingProgramRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TRAINING_PROGRAM_NOT_EXIST));
        if (!trainingProgram.getTrainingProgramId().equals(request.getTrainingProgramId())) {
            throw new AppException(ErrorCode.TRAINING_PROGRAM_ID_MISMATCH);
        }
        trainingProgramMapper.updateTrainingProgram(trainingProgram, request);
        return trainingProgramMapper.toTrainingProgramResponse(trainingProgramRepository.save(trainingProgram));
    }

    public void deleteTrainingProgram(String id) {
        var trainingProgram = trainingProgramRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TRAINING_PROGRAM_NOT_EXIST));
        trainingProgram.setStatus(false);
        trainingProgramRepository.save(trainingProgram);
    }

    public KnowledgeBlockResponse createKnowledgeBlock(CreateKnowledgeBlockRequest request) {
        TrainingProgram trainingProgram = trainingProgramRepository.findById(request.getTrainingCode())
                .orElseThrow(() -> new AppException(ErrorCode.TRAINING_PROGRAM_NOT_EXIST));
        if (knowledgeBlockRepository.existsById(request.getBlockCode()))
            throw new AppException(ErrorCode.KNOWLEDGE_BLOCK_EXISED);
        KnowledgeBlock knowledgeBlock = KnowledgeBlock.builder()
                .blockName(request.getBlockName())
                .blockCode(request.getBlockCode())
                .electiveCredits(request.getElectiveCredits())
                .requiredCredits(request.getRequiredCredits())
                .trainingProgram(trainingProgram)
                .build();
        List<KnowledgeBlockDetail> knowledgeBlockDetailList = new ArrayList<>();
        request.getKnowledgeBlockDetailList().forEach(knowledgeBlockDetail -> {
            KnowledgeBlockDetail knowledgeBlockDetail1 = KnowledgeBlockDetail.builder()
                    .knowledgeBlock(knowledgeBlock)
                    .requiredCredits(knowledgeBlockDetail.getRequiredCredits())
                    .electiveCredits(knowledgeBlockDetail.getElectiveCredits())
                    .typeName(knowledgeBlockDetail.getTypeName())
                    .build();
            knowledgeBlockDetailList.add(knowledgeBlockDetail1);
        });
        knowledgeBlock.setKnowledgeBlockDetails(knowledgeBlockDetailList);
        return trainingProgramMapper.toKnowledgeBockResponse(knowledgeBlockRepository.save(knowledgeBlock));
    }

    @Transactional
    public void deleteKnowledgeBlock(String id) {
        var knowledgeBlock = knowledgeBlockRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));
        knowledgeBlock.getKnowledgeBlockDetails().forEach(knowledgeBlockDetail -> {
            knowledgeBlockDetailRepository.deleteById(knowledgeBlockDetail.getId());
        });
        knowledgeBlockRepository.deleteById(id);
    }
}
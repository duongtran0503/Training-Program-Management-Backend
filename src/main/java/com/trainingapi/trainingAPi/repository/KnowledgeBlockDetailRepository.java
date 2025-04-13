package com.trainingapi.trainingAPi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface KnowledgeBlockDetailRepository extends JpaRepository<com.trainingapi.trainingAPi.entity.KnowledgeBlockDetail,String> {
    @Transactional
    void deleteByKnowledgeBlock_BlockCode(String knowledgeBlockId);
}

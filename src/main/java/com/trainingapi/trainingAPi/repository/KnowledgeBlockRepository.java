package com.trainingapi.trainingAPi.repository;

import com.trainingapi.trainingAPi.entity.KnowledgeBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface KnowledgeBlockRepository  extends JpaRepository<KnowledgeBlock,String> {
    @Transactional
    @Modifying
    @Query("DELETE FROM KnowledgeBlock kb WHERE kb.blockCode = :id")
    void deleteKnowledgeBlockById(@Param("id") String id);
}

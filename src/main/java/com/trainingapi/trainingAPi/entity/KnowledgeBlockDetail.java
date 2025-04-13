package com.trainingapi.trainingAPi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KnowledgeBlockDetail {
    @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     String id;
     String typeName;
    Integer requiredCredits;
    Integer electiveCredits;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "knowledge_block_id",referencedColumnName = "knowledge_block_id")
    KnowledgeBlock knowledgeBlock;

}

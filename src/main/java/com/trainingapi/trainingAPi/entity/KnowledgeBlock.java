package com.trainingapi.trainingAPi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KnowledgeBlock {
    @Id
    @Column(name = "knowledge_block_id")
    String blockCode;
    String blockName;
    Integer requiredCredits;
    Integer electiveCredits;

    @OneToMany(mappedBy = "knowledgeBlock",cascade ={CascadeType.REMOVE,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH},fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    List<KnowledgeBlockDetail> knowledgeBlockDetails;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "training_program_id",referencedColumnName = "training_program_id")
    TrainingProgram trainingProgram;

}

package com.trainingapi.trainingAPi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(length = 10)
    String username;

    String password;

    @Column(length = 20)
    String role;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    boolean status;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Date updateAt;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date createAt;
}

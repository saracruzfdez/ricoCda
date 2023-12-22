package com.cda.rico.repositories.recovery_password;

import com.cda.rico.repositories.security.UserRepositoryModel;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="recovery_password")
public class RecoveryPasswordRepositoryModel {
    // Marking the 'id' field as the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String code;

    // Relationship
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private UserRepositoryModel user;
}

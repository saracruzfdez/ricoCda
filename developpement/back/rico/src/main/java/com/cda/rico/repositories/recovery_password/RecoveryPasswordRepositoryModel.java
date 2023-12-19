package com.cda.rico.repositories.recovery_password;

import com.cda.rico.repositories.security.Owner;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name="recovery_password")
@Data
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
    private Owner user;
}

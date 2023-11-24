package com.cda.rico.repositories;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// Marking the class as a database entity
@Entity
// Specifying the table name for the entity
@Table(name="user")
@Getter
@Setter
public class UserRepositoryModel {
    // Marking the 'id' field as the primary key
    @Id
    // Generating the value for 'id' automatically
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Mapping the 'name' field to a column named "name" in the database table
    @Column(name = "name")
    private String name;

    // Mapping the 'email' field to a column named "email" in the database table
    @Column(name = "email")
    private String email;

    // Automatically generated no-argument constructor by Lombok
    public UserRepositoryModel() {}

    // Constructor with arguments to initialize 'name' and 'email'
    public  UserRepositoryModel(String name, String email)
    {
        this.name = name;
        this.email = email;
    }
}

package com.cda.rico.repositories.user;

import com.cda.rico.repositories.recipe.RecipeRepositoryModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// Marking the class as a database entity
@Entity
@NoArgsConstructor
@Table(name="user")
@Data
public class UserRepositoryModel {
    // Marking the 'id' field as the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String username;

    @Column
    private String role;

    @Column
    private String gender;

    @ManyToMany
    @JoinTable(
            name = "favorite_recipe",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<RecipeRepositoryModel> favorite_recipes = new ArrayList<>();
}

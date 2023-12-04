package com.cda.rico.repositories.step;

import com.cda.rico.repositories.recipe.RecipeRepositoryModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name="step")
@Data
public class StepRepositoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    // Relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private RecipeRepositoryModel recipeRepositoryModel;
}

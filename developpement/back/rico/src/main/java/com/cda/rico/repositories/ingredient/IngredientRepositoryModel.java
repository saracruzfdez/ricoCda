package com.cda.rico.repositories.ingredient;

import com.cda.rico.repositories.recipe.RecipeRepositoryModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
// Specifying the table name for the entity
@Table(name="ingredient")
@Data
public class IngredientRepositoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private int quantity;

    @Column
    private String unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private RecipeRepositoryModel recipeRepositoryModel;
}

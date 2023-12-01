package com.cda.rico.repositories.ingredient;

import com.cda.rico.enums.UnitEnum;
import com.cda.rico.repositories.recipe.RecipeRepositoryModel;
import jakarta.persistence.*;
import lombok.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
// Specifying the table name for the entity
@Table(name="ingredient")
@Getter
@Setter
public class IngredientRepositoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private int quantity;

    //@Enumerated(EnumType.STRING)
    @Column
    private String unit;

    //Relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private RecipeRepositoryModel recipeRepositoryModel;

    public void setRecipe_id(int recipeId) {
    }

    // POST, PLUS BESOIN GRACE A MAPSTRUCT QUI GENERE LE CONSTRUCTEUR

}




package com.cda.rico.repositories.ingredient;

import com.cda.rico.repositories.recipe.RecipeRepositoryModel;
import jakarta.persistence.*;
import lombok.*;
import lombok.NoArgsConstructor;

@Entity
// Specifying the table name for the entity
@Table(name="ingredient")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    //Relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private RecipeRepositoryModel recipeRepositoryModel;

    // POST, PLUS BESOIN GRACE A MAPSTRUCT QUI GENERE LE CONSTRUCTEUR

}




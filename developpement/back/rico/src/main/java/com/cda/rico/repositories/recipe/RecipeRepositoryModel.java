package com.cda.rico.repositories.recipe;

import com.cda.rico.repositories.ingredient.IngredientRepositoryModel;
import com.cda.rico.repositories.menu.MenuRepositoryModel;
import com.cda.rico.repositories.rating.RatingRepositoryModel;
import com.cda.rico.repositories.security.UserRepositoryModel;
import com.cda.rico.repositories.step.StepRepositoryModel;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
@Entity
@NoArgsConstructor
@Table(name="recipe")
@Getter
@Setter
public class RecipeRepositoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String image_path;

    @Column
    private String category;

    @Column
    private String title;

    @Column
    private int prep_time;

    @Column
    private int cook_time;

    @Column
    private int persons_number;

    @Column
    private String difficulty;

    @Column
    private String average_cost;

    @Column
    private String country_origin;

    // Relationship
    @OneToMany(mappedBy = "recipeRepositoryModel", cascade = CascadeType.ALL)
    private List<IngredientRepositoryModel> ingredients  = new ArrayList<>();

    @OneToMany(mappedBy = "recipeRepositoryModel", cascade = CascadeType.ALL)
    private List<StepRepositoryModel> steps  = new ArrayList<>();

    @ManyToOne
    private UserRepositoryModel user;

    @ManyToMany(mappedBy = "menuRecipes")
    private List<MenuRepositoryModel> menus = new ArrayList<>();

    @ManyToMany(mappedBy = "favoriteRecipes")
    private List<UserRepositoryModel> users = new ArrayList<>();

    @OneToMany(mappedBy = "recipeRepositoryModel")
    Set<RatingRepositoryModel> ratings;
}
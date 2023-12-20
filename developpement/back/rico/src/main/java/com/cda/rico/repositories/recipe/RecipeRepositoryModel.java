package com.cda.rico.repositories.recipe;

import com.cda.rico.repositories.ingredient.IngredientRepositoryModel;
import com.cda.rico.repositories.menu.MenuRepositoryModel;
import com.cda.rico.repositories.rating.RatingRepositoryModel;
import com.cda.rico.repositories.security.User;
import com.cda.rico.repositories.step.StepRepositoryModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// Marking the class as a database entity
@Entity
@NoArgsConstructor
@Table(name="recipe")
@Data
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
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "menuRecipes")
    private List<MenuRepositoryModel> menus = new ArrayList<>();

    @ManyToMany(mappedBy = "favoriteRecipes")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "recipeRepositoryModel")
    Set<RatingRepositoryModel> ratings;
}
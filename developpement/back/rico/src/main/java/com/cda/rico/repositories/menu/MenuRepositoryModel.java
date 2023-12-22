package com.cda.rico.repositories.menu;

import com.cda.rico.repositories.recipe.RecipeRepositoryModel;
import com.cda.rico.repositories.security.UserRepositoryModel;
import com.cda.rico.repositories.shopping_list_ingredient.ShoppingListIngredientRepositoryModel;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="menu")
public class MenuRepositoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    //Relationship
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserRepositoryModel user;

    @ManyToMany
    @JoinTable(
            name = "menu_recipe",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<RecipeRepositoryModel> menuRecipes = new ArrayList<>();

    @OneToMany(mappedBy = "menuRepositoryModel", cascade = CascadeType.ALL)
    private List<ShoppingListIngredientRepositoryModel> shoppingListIngredients  = new ArrayList<>();

    public void asociarReceta(RecipeRepositoryModel recipeRepositoryModel) {
        menuRecipes.add(recipeRepositoryModel);
        recipeRepositoryModel.getMenus().add(this);
    }


}
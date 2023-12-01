package com.cda.rico.repositories.menu;

import com.cda.rico.repositories.recipe.RecipeRepositoryModel;
import com.cda.rico.repositories.shopping_list_ingredient.ShoppingListIngredientRepositoryModel;
import com.cda.rico.repositories.user.UserRepositoryModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name="menu")
@Data
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
    private UserRepositoryModel userRepositoryModel;

    @ManyToMany
    @JoinTable(
            name = "menu_recipe",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<RecipeRepositoryModel> menuRecipes = new ArrayList<>();

    @OneToMany(mappedBy = "menuRepositoryModel", cascade = CascadeType.ALL)
    private List<ShoppingListIngredientRepositoryModel> shoppingListIngredients  = new ArrayList<>();

}
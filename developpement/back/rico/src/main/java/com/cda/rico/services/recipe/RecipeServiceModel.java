package com.cda.rico.services.recipe;

import com.cda.rico.enums.AverageCostEnum;
import com.cda.rico.enums.CategoryEnum;
import com.cda.rico.enums.DifficultyEnum;

import com.cda.rico.services.ingredient.IngredientServiceModel;
import com.cda.rico.services.step.StepServiceModel;
import lombok.Data;

import java.util.List;

@Data
public class RecipeServiceModel {

    private int id;
    private String image_path;
    private CategoryEnum category;
    private String title;
    private int prep_time;
    private int cook_time;
    private int persons_number;
    private DifficultyEnum difficulty;
    private AverageCostEnum average_cost;
    private String country_origin;
    private List<IngredientServiceModel> ingredients;
    private List<StepServiceModel> steps;
    private int userId;
/*    private List<MenuServiceModel> menus;
    private List<UserServiceModel> users;
    private Set<RatingServiceModel> ratings;*/

    // POST, PLUS BESOIN GRACE A MAPSTRUCT QUI GENERE LE CONSTRUCTEUR

}

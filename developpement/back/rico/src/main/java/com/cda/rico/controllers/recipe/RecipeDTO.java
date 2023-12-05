package com.cda.rico.controllers.recipe;

import com.cda.rico.controllers.ingredient.IngredientDTO;
import com.cda.rico.controllers.step.StepDTO;
import com.cda.rico.enums.AverageCostEnum;
import com.cda.rico.enums.CategoryEnum;
import com.cda.rico.enums.DifficultyEnum;
import com.cda.rico.services.ingredient.IngredientServiceModel;

import java.util.List;

public record RecipeDTO (
        int id,
        String image_path,
        String category,
        String title,
        int prep_time,
        int cook_time,
        int persons_number,
        String difficulty,
        String average_cost,
        String country_origin,
        List<IngredientDTO> ingredients,
        List<StepDTO> steps
){
        }
package com.cda.rico.controllers.recipe;

import com.cda.rico.controllers.ingredient.IngredientDTO;
import com.cda.rico.controllers.step.StepDTO;

import java.util.List;

public record RecipeDTO(
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

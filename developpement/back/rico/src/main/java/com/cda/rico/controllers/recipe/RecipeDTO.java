package com.cda.rico.controllers.recipe;

import com.cda.rico.controllers.ingredient.IngredientGetDTO;
import com.cda.rico.controllers.step.StepGetDTO;

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
        List<IngredientGetDTO> ingredients,
        List<StepGetDTO> steps
){
        }
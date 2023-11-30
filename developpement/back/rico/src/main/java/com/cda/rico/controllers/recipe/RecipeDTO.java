package com.cda.rico.controllers.recipe;

import com.cda.rico.enums.AverageCostEnum;
import com.cda.rico.enums.CategoryEnum;
import com.cda.rico.enums.DifficultyEnum;

public record RecipeDTO (String image_path, String category, String title, int prep_time, int cook_time, int persons_number, String difficulty, String average_cost, String country_origin){
        }
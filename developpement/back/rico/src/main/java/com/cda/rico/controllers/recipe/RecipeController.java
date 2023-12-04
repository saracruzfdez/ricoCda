package com.cda.rico.controllers.recipe;

import com.cda.rico.mappers.RecipeMapper;
import com.cda.rico.services.recipe.RecipeService;
import com.cda.rico.services.recipe.RecipeServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
   private RecipeService recipeService;

    @PostMapping
    public boolean addRecipeToBdd(@RequestBody RecipeDTO recipeDTO){
        RecipeServiceModel recipeServiceModel = RecipeMapper.INSTANCE.dtoToServiceModel(recipeDTO);

         return recipeService.add(recipeServiceModel);


        //return recipeService.add(recipeServiceModel);


    }

}

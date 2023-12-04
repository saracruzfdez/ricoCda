package com.cda.rico.services.recipe;


import com.cda.rico.mappers.RecipeMapper;
import com.cda.rico.repositories.recipe.RecipeRepository;
import com.cda.rico.repositories.recipe.RecipeRepositoryModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;



    public boolean add(RecipeServiceModel recipeServiceModel){

        RecipeRepositoryModel recipeRepositoryModel = RecipeMapper.INSTANCE.serviceToRepository(recipeServiceModel);

        RecipeRepositoryModel recipeRepositoryModelReturned = recipeRepository.save(recipeRepositoryModel);

        return recipeRepositoryModelReturned != null;
    }
}

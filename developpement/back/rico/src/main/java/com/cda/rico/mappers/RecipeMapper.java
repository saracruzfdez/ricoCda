package com.cda.rico.mappers;

import com.cda.rico.controllers.recipe.RecipeDTO;
import com.cda.rico.repositories.recipe.RecipeRepositoryModel;
import com.cda.rico.services.recipe.RecipeServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipeMapper {

    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    //POUR LE POST
    RecipeServiceModel dtoToServiceModel (RecipeDTO recipeDTO);

    RecipeRepositoryModel serviceToRepository(RecipeServiceModel serviceModel);
}

package com.cda.rico.mappers;

import com.cda.rico.controllers.ingredient.IngredientDTO;
import com.cda.rico.repositories.ingredient.IngredientRepositoryModel;
import com.cda.rico.services.ingredient.IngredientServiceModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    // POUR LE POST :
    IngredientServiceModel dtoToServiceModel(IngredientDTO ingredientDTO);
    IngredientRepositoryModel serviceToRepository(IngredientServiceModel serviceModel);
}

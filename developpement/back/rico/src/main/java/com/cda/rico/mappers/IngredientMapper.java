package com.cda.rico.mappers;

import com.cda.rico.controllers.ingredient.IngredientDTO;
import com.cda.rico.repositories.ingredient.IngredientRepositoryModel;
import com.cda.rico.services.ingredient.IngredientServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IngredientMapper {

    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);
    // POUR LE POST :
    IngredientServiceModel dtoToServiceModel(IngredientDTO ingredientDTO);
    IngredientRepositoryModel serviceToRepository(IngredientServiceModel serviceModel);

}

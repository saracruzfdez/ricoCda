package com.cda.rico.repositories.ingredient;

import com.cda.rico.enums.UnitEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<IngredientRepositoryModel, Integer> {

    //Convert between types in the repository (IngredientRepository):
    default IngredientRepositoryModel saveWithUnitEnum(IngredientRepositoryModel ingredientRepositoryModel, UnitEnum unitEnum) {
// Convert UnitEnum to String before saving to the repository
        ingredientRepositoryModel.setUnit(unitEnum.name());
        return save(ingredientRepositoryModel);
    }

    default IngredientRepositoryModel findByUnitEnum(UnitEnum unitEnum) {
// Convert String to UnitEnum when retrieving from the repository
        return findByUnit(unitEnum.name());
    }

    IngredientRepositoryModel findByUnit(String unit);

}

package com.cda.rico.services.recipe;

import com.cda.rico.mappers.RecipeMapper;
import com.cda.rico.repositories.ingredient.IngredientRepository;
import com.cda.rico.repositories.ingredient.IngredientRepositoryModel;
import com.cda.rico.repositories.recipe.RecipeRepository;
import com.cda.rico.repositories.recipe.RecipeRepositoryModel;

import com.cda.rico.repositories.step.StepRepositoryModel;
import com.cda.rico.services.ingredient.IngredientServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    // Method to add a recipe
    public boolean add(RecipeServiceModel recipeServiceModel) {

        // Map RecipeServiceModel to RecipeRepositoryModel
        RecipeRepositoryModel recipeRepositoryModel = RecipeMapper.INSTANCE.serviceToRepository(recipeServiceModel);

        // Get the list of ingredients from RecipeServiceModel (because id_recipe was null in recipeServiceModel (JPA needs the entire object, not only the id)) :
        /*List<IngredientServiceModel> ingredientServiceModels = recipeServiceModel.getIngredients();*/

        // Initialize the list of ingredients in the recipe repository model (in order to map all the attributes manually not with mapstruct, so we can set all the values and have the value of id_recipe) :
        /*recipeRepositoryModel.setIngredients(new ArrayList<>());*/
        // Loop through each ingredient in the service model
        /* for (IngredientServiceModel ingredientServiceModel : ingredientServiceModels) {
            // Create a new IngredientRepositoryModel and add it to the list of ingredients in the recipe repository model
            recipeRepositoryModel.getIngredients().add(
                    new IngredientRepositoryModel(
                            ingredientServiceModel.getId(),
                            ingredientServiceModel.getName(),
                            ingredientServiceModel.getQuantity(),
                            ingredientServiceModel.getUnit().toString(),
                            // Retrieve the corresponding recipe from the database based on the recipe_id in the service model
                            recipeRepository.findById(ingredientServiceModel.getRecipe_id()).get())
            );
        }*/

        for(IngredientRepositoryModel ingredientRepositoryModel : recipeRepositoryModel.getIngredients())
        {
            ingredientRepositoryModel.setRecipeRepositoryModel(recipeRepositoryModel);
        }

        for(StepRepositoryModel stepRepositoryModel : recipeRepositoryModel.getSteps())
        {
            stepRepositoryModel.setRecipeRepositoryModel(recipeRepositoryModel);
        }

        // Save the recipe in the database
        RecipeRepositoryModel recipeRepositoryModelReturned = recipeRepository.save(recipeRepositoryModel);

        // Return true if the recipe was successfully saved
        return recipeRepositoryModelReturned != null;
    }

    public void deleteById(int id) {recipeRepository.deleteById(id);}

    public boolean update(int id, RecipeServiceModel  updatedRecipe) {

        RecipeRepositoryModel recipeRepositoryModel = RecipeMapper.INSTANCE.serviceToRepository(updatedRecipe);

        Optional<RecipeRepositoryModel> existingRecipe = recipeRepository.findById(id);
        if (existingRecipe.isPresent()) {
            RecipeRepositoryModel recipeToUpdate = existingRecipe.get();

            recipeToUpdate.setImage_path(updatedRecipe.getImage_path());
            recipeToUpdate.setCategory(updatedRecipe.getCategory().toString());
            recipeToUpdate.setTitle(updatedRecipe.getTitle());
            recipeToUpdate.setPrep_time(updatedRecipe.getPrep_time());
            recipeToUpdate.setCook_time(updatedRecipe.getCook_time());
            recipeToUpdate.setPersons_number(updatedRecipe.getPersons_number());
            recipeToUpdate.setDifficulty(updatedRecipe.getDifficulty().toString());
            recipeToUpdate.setAverage_cost(updatedRecipe.getAverage_cost().toString());
            recipeToUpdate.setCountry_origin(updatedRecipe.getCountry_origin());


//            // Update ingredients list
//            List<IngredientServiceModel> updatedIngredients = updatedRecipe.getIngredients();
//            if (updatedIngredients != null) {
//                // Limpiar la lista existente y agregar los nuevos ingredientes
//                recipeToUpdate.getIngredients().clear();
//                recipeToUpdate.getIngredients().addAll(updatedIngredients);
//            }


            RecipeRepositoryModel recipeRepositoryModelReturned = recipeRepository.save(recipeToUpdate);
            return recipeRepositoryModelReturned != null;

        } else {
            return false;
        }
    }
}
package com.cda.rico.services.recipe;

import com.cda.rico.mappers.RecipeMapper;
import com.cda.rico.repositories.ingredient.IngredientRepository;
import com.cda.rico.repositories.ingredient.IngredientRepositoryModel;
import com.cda.rico.repositories.recipe.RecipeRepository;
import com.cda.rico.repositories.recipe.RecipeRepositoryModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        /*
        for(IngredientRepositoryModel ingredientRepositoryModel : recipeRepositoryModel.getIngredients())
        {
            ingredientRepositoryModel.setRecipeRepositoryModel(recipeRepositoryModel);
        }

        // Iterate through the list of steps in the recipeRepositoryModel
        for (StepRepositoryModel stepRepositoryModel : recipeRepositoryModel.getSteps()) {
            // Set the recipeRepositoryModel for each step in the loop
            stepRepositoryModel.setRecipeRepositoryModel(recipeRepositoryModel);
        }*/

        // Save the recipe in the database
        RecipeRepositoryModel recipeRepositoryModelReturned = recipeRepository.save(recipeRepositoryModel);

        recipeRepositoryModelReturned.getIngredients().stream().map((x) -> ingredientRepository.save(new IngredientRepositoryModel(
                x.getId(), x.getName(), x.getQuantity(), x.getUnit(),recipeRepositoryModel
        ))).toList();
        // Return true if the recipe was successfully saved
        return recipeRepositoryModelReturned != null;

        ///////////////////////////////////////////////////
        // HACER LO MISMO PARA LOS PASOS !!!
        // ACABAR DE UPDATE LAS LISTAS INGREDIENTES Y PASOS
        ///////////////////////////////////////////////////

    }


    public boolean update(int id, RecipeServiceModel updatedRecipe) {

        // Convert the updatedRecipe from a service model to a repository model
        RecipeRepositoryModel recipeRepositoryModel = RecipeMapper.INSTANCE.serviceToRepository(updatedRecipe);

        // Retrieve the existing recipe from the database based on the provided ID
        Optional<RecipeRepositoryModel> existingRecipe = recipeRepository.findById(id);

        // Check if the recipe with the given ID exists in the database
        if (existingRecipe.isPresent()) {
            // If the recipe exists, retrieve it for modification
            RecipeRepositoryModel recipeToUpdate = existingRecipe.get();

            // Update various fields of the existing recipe with the information from the updatedRecipe
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




    public void deleteById(int id) {recipeRepository.deleteById(id);}





    public List<RecipeServiceModel> getAll(){
        List<RecipeRepositoryModel> recipeRepositoryModels = (List<RecipeRepositoryModel>) recipeRepository.findAll();
        return recipeRepositoryModels.stream()
                .map(RecipeMapper.INSTANCE::repositoryToService).toList();
    }
}
package com.cda.rico.services.recipe;

import com.cda.rico.mappers.RecipeMapper;
import com.cda.rico.repositories.ingredient.IngredientRepository;
import com.cda.rico.repositories.ingredient.IngredientRepositoryModel;
import com.cda.rico.repositories.recipe.RecipeRepository;
import com.cda.rico.repositories.recipe.RecipeRepositoryModel;

import com.cda.rico.repositories.security.UserRepositoryModel;
import com.cda.rico.repositories.security.UserRepository;
import com.cda.rico.repositories.step.StepRepository;
import com.cda.rico.repositories.step.StepRepositoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private StepRepository stepRepository;

    @Autowired
    private UserRepository userRepository;

    // Method to add a recipe
    public boolean add(int userId, RecipeServiceModel recipeServiceModel) {
        Optional<UserRepositoryModel> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            UserRepositoryModel user = userOptional.get();

            // Map RecipeServiceModel to RecipeRepositoryModel
            RecipeRepositoryModel recipeRepositoryModel = RecipeMapper.INSTANCE.serviceToRepository(recipeServiceModel);

            recipeRepositoryModel.setUser(user);

            // Guardar la receta en la base de datos
            RecipeRepositoryModel recipeRepositoryModelReturned = recipeRepository.save(recipeRepositoryModel);

            recipeRepositoryModelReturned.getIngredients().stream().map((x) -> ingredientRepository.save(new IngredientRepositoryModel(
                    x.getId(), x.getName(), x.getQuantity(), x.getUnit(),recipeRepositoryModel
            ))).toList();

            recipeRepositoryModelReturned.getSteps().stream().map((x) -> stepRepository.save(new StepRepositoryModel(x.getId(), x.getName(), x.getDescription(), recipeRepositoryModel))).toList();

            // Actualizar el usuario con la receta creada
            user.getCreatedRecipes().add(recipeRepositoryModelReturned);
            userRepository.save(user);

            return recipeRepositoryModelReturned != null;
        }
        return false;
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

// Update ingredients list
            List<IngredientRepositoryModel> updatedIngredients = updatedRecipe.getIngredients().stream()
                    .map(ingredient -> {
                        // Buscar ingredientes existentes por nombre
                        Optional<IngredientRepositoryModel> existingIngredientOptional = recipeToUpdate.getIngredients().stream()
                                .filter(existingIngredient -> existingIngredient.getName().equals(ingredient.getName()))
                                .findFirst();

                        if (existingIngredientOptional.isPresent()) {
                            // Si existe, actualizar el ingrediente existente
                            IngredientRepositoryModel existingIngredient = existingIngredientOptional.get();
                            existingIngredient.setQuantity(ingredient.getQuantity());
                            existingIngredient.setUnit(ingredient.getUnit().toString());
                            return existingIngredient;
                        } else {
                            // Si no existe, crear un nuevo ingrediente
                            return new IngredientRepositoryModel(
                                    ingredient.getId(), ingredient.getName(), ingredient.getQuantity(),
                                    ingredient.getUnit().toString(), recipeToUpdate
                            );
                        }
                    })
                    .collect(Collectors.toList());

            recipeToUpdate.setIngredients(updatedIngredients);

// Update steps list
            List<StepRepositoryModel> updatedSteps = updatedRecipe.getSteps().stream()
                    .map(step -> {
                        // Buscar pasos existentes por nombre
                        Optional<StepRepositoryModel> existingStepOptional = recipeToUpdate.getSteps().stream()
                                .filter(existingStep -> existingStep.getName().equals(step.getName()))
                                .findFirst();

                        if (existingStepOptional.isPresent()) {
                            // Si existe, actualizar el paso existente
                            StepRepositoryModel existingStep = existingStepOptional.get();
                            existingStep.setName(step.getName());
                            existingStep.setDescription(step.getDescription());
                            return existingStep;
                        } else {
                            // Si no existe, crear un nuevo paso
                            return new StepRepositoryModel(step.getId(), step.getName(), step.getDescription(), recipeToUpdate);
                        }
                    })
                    .collect(Collectors.toList());

            recipeToUpdate.setSteps(updatedSteps);

            // Save the updated recipe to the database
            RecipeRepositoryModel recipeRepositoryModelReturned = recipeRepository.save(recipeToUpdate);
            return recipeRepositoryModelReturned != null;

        } else {
            return false; // Recipe not found
        }
    }

    public void deleteById(int id) {recipeRepository.deleteById(id);}

    public List<RecipeServiceModel> getAll(){
        List<RecipeRepositoryModel> recipeRepositoryModels = (List<RecipeRepositoryModel>) recipeRepository.findAll();
        return recipeRepositoryModels.stream()
                .map(RecipeMapper.INSTANCE::repositoryToService).toList();
    }

    public RecipeServiceModel getById(int id) {
        Optional<RecipeRepositoryModel> recipeRepositoryModelOptional = recipeRepository.findById(id);
        if (recipeRepositoryModelOptional.isPresent()){
            return RecipeMapper.INSTANCE.repositoryToService(recipeRepositoryModelOptional.get());
        } else {
            return null;
        }
    }
}
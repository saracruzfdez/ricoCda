package com.cda.rico.controllers.recipe;

import com.cda.rico.mappers.RecipeMapper;
import com.cda.rico.repositories.recipe.RecipeRepository;
import com.cda.rico.services.recipe.RecipeService;
import com.cda.rico.services.recipe.RecipeServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private RecipeRepository recipeRepository;

    @PostMapping
    public boolean addRecipeToBdd(@RequestBody RecipeDTO recipeDTO){
        RecipeServiceModel recipeServiceModel = RecipeMapper.INSTANCE.dtoToServiceModel(recipeDTO);
        // System.out.println(recipeDTO);
        return recipeService.add(recipeServiceModel);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        recipeRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable int id, @RequestBody RecipeDTO updatedRecipeDTO){
        RecipeServiceModel updatedRecipeServiceModel = RecipeMapper.INSTANCE.dtoToServiceModel(updatedRecipeDTO);
        recipeService.update(id, updatedRecipeServiceModel);

        return updatedRecipeServiceModel != null;
    }

    @GetMapping
    public List<RecipeDTO> getAll(){
        List<RecipeServiceModel> recipeServiceModels = recipeService.getAll();
        return recipeServiceModels.stream()
                .map(RecipeMapper.INSTANCE::recipeServiceModelToDTO)
                .collect(Collectors.toList());
    }

}

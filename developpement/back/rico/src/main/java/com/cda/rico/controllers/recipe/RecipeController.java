package com.cda.rico.controllers.recipe;

import com.cda.rico.mappers.RecipeMapper;
import com.cda.rico.repositories.recipe.RecipeRepository;
import com.cda.rico.services.recipe.RecipeService;
import com.cda.rico.services.recipe.RecipeServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private RecipeRepository recipeRepository;

    @PostMapping("/users/{id}/recipes")
    public boolean addRecipeToBdd(@PathVariable int id, @RequestBody RecipeDTO recipeDTO){

        RecipeServiceModel recipeServiceModel = RecipeMapper.INSTANCE.dtoToServiceModel(recipeDTO);
        // System.out.println(recipeDTO);
        boolean success = recipeService.add(id, recipeServiceModel);
        if (success) {
            return ResponseEntity.ok("Receta creada exitosamente para el usuario con ID: " + id).hasBody();
        } else {
            return ResponseEntity.badRequest().body("No se pudo crear la receta. Usuario no encontrado.").hasBody();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        recipeRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public boolean update(@PathVariable int id, @RequestBody RecipeGetDTO updatedRecipeDTO){
        RecipeServiceModel updatedRecipeServiceModel = RecipeMapper.INSTANCE.dtoGetToServiceModel(updatedRecipeDTO);
        recipeService.update(id, updatedRecipeServiceModel);

        return updatedRecipeServiceModel != null;
    }
    @GetMapping
    public List<RecipeGetDTO> getAll(){
        List<RecipeServiceModel> recipeServiceModels = recipeService.getAll();
        return recipeServiceModels.stream()
                .map(RecipeMapper.INSTANCE::recipeServiceModelToDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public RecipeGetDTO getById(@PathVariable int id){
        RecipeServiceModel recipeServiceModel = recipeService.getById(id);
            RecipeGetDTO recipeGetDTO = RecipeMapper.INSTANCE.recipeServiceModelToDTO(recipeServiceModel);
            return recipeGetDTO;
    }
}
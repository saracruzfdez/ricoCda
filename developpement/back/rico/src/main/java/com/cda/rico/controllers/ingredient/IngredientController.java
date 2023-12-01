// package com.cda.rico.controllers.ingredient;
/*
import com.cda.rico.mappers.IngredientMapper;
import com.cda.rico.services.ingredient.IngredientService;
import com.cda.rico.services.ingredient.IngredientServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;
    private final IngredientMapper ingredientMapper;

    @Autowired
    public IngredientController(IngredientService ingredientService, IngredientMapper ingredientMapper){
        this.ingredientService = ingredientService;
        this.ingredientMapper = ingredientMapper;
    }

    @PostMapping
    public boolean addIngredientToBdd(@RequestBody IngredientDTO ingredientDTO){
        IngredientServiceModel ingredientServiceModel = ingredientMapper.dtoToServiceModel(ingredientDTO);
        return ingredientService.add(ingredientServiceModel);
    }
}
*/

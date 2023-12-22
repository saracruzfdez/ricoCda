package com.cda.rico.controllers.menu;

import com.cda.rico.mappers.MenuMapper;
import com.cda.rico.repositories.menu.MenuRepository;
import com.cda.rico.services.menu.MenuService;
import com.cda.rico.services.menu.MenuServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menus")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @PostMapping("/users/{id}/menus")
    public boolean addMenuToBdd(@PathVariable int id, @RequestBody MenuDTO menuDTO){
        MenuServiceModel menuServiceModel = MenuMapper.INSTANCE.dtoToServiceModel(menuDTO);

        boolean success = menuService.add(id, menuServiceModel);
        if(success){
            return ResponseEntity.ok("Menu ajouté" + id).hasBody();
        }else{
        return ResponseEntity.badRequest().body("Menu non ajouté.").hasBody();
        }
    }
    @PostMapping("/{menuId}/join-recipe/{recipeId}")
    public boolean joinRecipeToMenu(@PathVariable int menuId, @PathVariable int recipeId){
        try{
            // Aquí se asocia la receta al menú
            menuService.joinRecipeToMenu(menuId, recipeId);
            return true; // Si llega hasta aquí, la asociación fue exitosa
        } catch (Exception e) {
            // Devuelve false si hay algún otro error
            return false;
        }
    }
}
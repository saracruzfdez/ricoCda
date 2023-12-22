package com.cda.rico.services.menu;

import com.cda.rico.mappers.MenuMapper;
import com.cda.rico.repositories.menu.MenuRepository;
import com.cda.rico.repositories.menu.MenuRepositoryModel;
import com.cda.rico.repositories.recipe.RecipeRepository;
import com.cda.rico.repositories.recipe.RecipeRepositoryModel;
import com.cda.rico.repositories.security.UserRepository;
import com.cda.rico.repositories.security.UserRepositoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecipeRepository recipeRepository;

    public boolean add(int userId, MenuServiceModel menuServiceModel){
        Optional<UserRepositoryModel> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            UserRepositoryModel user = userOptional.get();

            MenuRepositoryModel menuRepositoryModel = MenuMapper.INSTANCE.serviceToRepository(menuServiceModel);

        menuRepositoryModel.setUser(user);

        MenuRepositoryModel menuRepositoryModelReturned = menuRepository.save(menuRepositoryModel);

        user.getCreatedMenus().add(menuRepositoryModelReturned);
        userRepository.save(user);

        return menuRepositoryModelReturned != null;
        }
        return false;
}

    public boolean joinRecipeToMenu(int menuId, int recipeId) {
        try {
            MenuRepositoryModel menuRepositoryModel = menuRepository.findById(menuId)
                    .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
            RecipeRepositoryModel recipeRepositoryModel=recipeRepository.findById(recipeId)
                    .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

                    if (!menuRepositoryModel.getMenuRecipes().contains(recipeRepositoryModel)){
                        menuRepositoryModel.getMenuRecipes().add(recipeRepositoryModel);
                        menuRepository.save(menuRepositoryModel);
                    }
                    return true;
        }catch (ChangeSetPersister.NotFoundException e){
            return false;
        }catch (Exception e){
return false;
        }
    }
}

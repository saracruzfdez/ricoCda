package com.cda.rico.mappers;

import com.cda.rico.controllers.menu.MenuDTO;
import com.cda.rico.controllers.menu.MenuGetDTO;
import com.cda.rico.repositories.menu.MenuRepositoryModel;
import com.cda.rico.services.menu.MenuServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MenuMapper {

    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);

    //POUR LE POST
    MenuServiceModel dtoToServiceModel (MenuDTO menuDTO);

    MenuRepositoryModel serviceToRepository(MenuServiceModel serviceModel);

    //GET
    MenuServiceModel repositoryToService(MenuRepositoryModel menuRepositoryModel);

    MenuGetDTO menuServiceModelToDTO(MenuServiceModel menuServiceModel);

    MenuServiceModel dtoGetToServiceModel (MenuGetDTO menuGetDTO);


}

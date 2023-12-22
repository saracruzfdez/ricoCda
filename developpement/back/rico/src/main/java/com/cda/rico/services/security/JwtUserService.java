package com.cda.rico.services.security;

import com.cda.rico.exceptions.AccountExistsException;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtUserService extends UserService{
    String generateJwtForUser(UserDetails user);

    UserDetails save(String username, String password) throws AccountExistsException;


    UserDetails getUserFromJwt(String jwt);

    int getUserIdFromJwt(String jwt); // Nuevo método para obtener el ID del usuario


}
//Remarque : Permet de découpler l'implémentation de la vérification utilisateur avec le JWT, peut-etre mocker dans
//les tests unitaires
package com.cda.rico.services.security;

import com.cda.rico.exceptions.AccountExistsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Authentication authenticate(String username, String password) throws Exception;
    UserDetails save(String username, String password) throws AccountExistsException;

}
//Remarque : Permet de découpler les couches
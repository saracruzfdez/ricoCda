package com.cda.rico.services.security.impl;

import com.cda.rico.enums.RoleEnum;
import com.cda.rico.exceptions.AccountExistsException;
import com.cda.rico.repositories.security.UserRepositoryModel;
import com.cda.rico.repositories.security.UserRepository;
import com.cda.rico.services.security.JwtUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUserServiceImpl implements JwtUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final String signingKey;
    public JwtUserServiceImpl(@Value("${jwt.signing.key}") String signingKey) {
        this.signingKey = signingKey;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        UserRepositoryModel user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("The owner could not be found");
        }
        return user;
    }

    // USED FOR REGISTRATION

    @Override
    public Authentication authenticate(String username, String password) throws Exception {
        Authentication authentication = new
                UsernamePasswordAuthenticationToken(username, password);
        return authenticationConfiguration.getAuthenticationManager().authenticate(authentication);
    }


    @Override
    public UserDetails save(String username, String password) throws AccountExistsException {
        UserDetails existingUser = userRepository.findByEmail(username);
        if (existingUser != null) {
            throw new AccountExistsException();
        }
        UserRepositoryModel user = new UserRepositoryModel();
        user.setEmail(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }

    public UserRepositoryModel saveUser(String username, String password) throws AccountExistsException {
        UserRepositoryModel existingUser = userRepository.findByEmail(username);
        if (existingUser != null) {
            throw new AccountExistsException();
        }
        UserRepositoryModel user = new UserRepositoryModel();
        user.setRole_user("user");
        user.setEmail(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }

    //USED FOR AUTHENTIFICATION

    @Override
    public UserDetails getUserFromJwt(String jwt) {
        String username = getUsernameFromToken(jwt);
        return loadUserByUsername(username);
    }

    private String getUsernameFromToken(String token) {
        System.out.println(signingKey);
        Claims claims =
                Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }


    @Override
    public String generateJwtForUser(UserDetails user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600 * 1000);
        return
                Jwts.builder().setSubject(user.getUsername()).setIssuedAt(now).setExpiration(expiryDate)
                        .signWith(SignatureAlgorithm.HS512, signingKey)
                        .compact();
    }
    @Override
    public int getUserIdFromJwt(String jwt) {
        String username = getUsernameFromToken(jwt);
        UserDetails userDetails = loadUserByUsername(username);

        if (userDetails instanceof UserRepositoryModel) {
            return ((UserRepositoryModel) userDetails).getId();
        } else {
            throw new IllegalStateException("No se pudo obtener el ID del usuario desde el token JWT.");
        }
    }

}

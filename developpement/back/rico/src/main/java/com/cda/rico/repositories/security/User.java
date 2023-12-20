package com.cda.rico.repositories.security;

import com.cda.rico.enums.RoleEnum;
import com.cda.rico.repositories.menu.MenuRepositoryModel;
import com.cda.rico.repositories.rating.RatingRepositoryModel;
import com.cda.rico.repositories.recipe.RecipeRepositoryModel;
import com.cda.rico.repositories.recovery_password.RecoveryPasswordRepositoryModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, length = 255)
    private String email;

    private String password;

    private String gender;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MenuRepositoryModel> createdMenus = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "favorite_recipe",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<RecipeRepositoryModel> favoriteRecipes = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private RecoveryPasswordRepositoryModel recoveryPasswordRepositoryModel;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<RecipeRepositoryModel> createdRecipes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<RatingRepositoryModel> ratings;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
//Remarque : Ce qui marque un utilisateur, c'est cette ligne : implements UserDetails

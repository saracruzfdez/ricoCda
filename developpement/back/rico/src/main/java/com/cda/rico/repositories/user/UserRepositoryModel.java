package com.cda.rico.repositories.user;

import com.cda.rico.repositories.menu.MenuRepositoryModel;
import com.cda.rico.repositories.recipe.RecipeRepositoryModel;
import com.cda.rico.repositories.recovery_password.RecoveryPasswordRepositoryModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// Marking the class as a database entity
@Entity
@NoArgsConstructor
@Table(name="user")
@Data
public class UserRepositoryModel {
    // Marking the 'id' field as the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String username;
    @Column
    private String role;
    @Column
    private String gender;

    @OneToMany(mappedBy = "userRepositoryModel", cascade = CascadeType.ALL)
    private List<MenuRepositoryModel> createdMenus = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "favorite_recipe",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<RecipeRepositoryModel> favoriteRecipes = new ArrayList<>();

    @OneToOne(mappedBy = "userRepositoryModel", cascade = CascadeType.ALL)
    private RecoveryPasswordRepositoryModel recoveryPasswordRepositoryModel;

    @OneToMany(mappedBy = "userRepositoryModel", cascade = CascadeType.ALL)
    private List<RecipeRepositoryModel> createdRecipes = new ArrayList<>();

    /* @ElementCollection
    @CollectionTable(name = "rating", joinColumns = @JoinColumn(name = "user_id"))
    @MapKeyJoinColumn(name = "recipe_id")
    @Column(name = "rating")
    // @MapKeyJoinColumn(name = "recipe_id")
    // @Column(name = "description") //error duplicated anotation...
    private Map<RecipeRepositoryModel, RatingRepositoryModel> ratings; */
}

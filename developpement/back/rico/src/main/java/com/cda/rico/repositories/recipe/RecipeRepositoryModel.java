package com.cda.rico.repositories.recipe;

import com.cda.rico.repositories.menu.MenuRepositoryModel;
import com.cda.rico.repositories.user.UserRepositoryModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// Marking the class as a database entity
@Entity
@NoArgsConstructor
@Table(name="recipe")
@Data
public class RecipeRepositoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String image_path;

    @Column
    private String category;

    @Column
    private String title;

    @Column
    private int prep_time;

    @Column
    private int cook_time;

    @Column
    private int persons_number;

    @Column
    private String difficulty;

    @Column
    private String average_cost;

    @Column
    private String country_origin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id")
    private UserRepositoryModel userRepositoryModel;

    @ManyToMany(mappedBy = "menu_recipes")
    private List<MenuRepositoryModel> menus = new ArrayList<>();

    @ManyToMany(mappedBy = "favorite_recipes")
    private List<UserRepositoryModel> users = new ArrayList<>();
    
}
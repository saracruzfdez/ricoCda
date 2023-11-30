/* package com.cda.rico.repositories.rating;

import com.cda.rico.repositories.recipe.RecipeRepositoryModel;
import com.cda.rico.repositories.user.UserRepositoryModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name="rating")
@Data
public class RatingRepositoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private int rating_value;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id")
    private UserRepositoryModel userRepositoryModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private RecipeRepositoryModel recipeRepositoryModel;
}*/

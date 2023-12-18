package com.cda.rico.repositories.recipe;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RecipeRepository extends CrudRepository<RecipeRepositoryModel, Integer> {
    Optional<RecipeRepositoryModel> findById(int id);
}

package com.cda.rico.repositories.menu;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MenuRepository extends CrudRepository<MenuRepositoryModel, Integer> {
        Optional<MenuRepositoryModel> findById(int id);

        }


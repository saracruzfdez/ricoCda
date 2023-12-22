package com.cda.rico.repositories.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserRepositoryModel, Integer> {
//    Owner findByLogin(String login);
    UserRepositoryModel findByEmail(String email);
    Optional<UserRepositoryModel> findById(int id);

}
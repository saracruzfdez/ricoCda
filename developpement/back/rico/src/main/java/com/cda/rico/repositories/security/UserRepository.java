package com.cda.rico.repositories.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
//    Owner findByLogin(String login);
    User findByEmail(String email);
}
package com.cda.rico.repositories.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Integer> {
//    Owner findByLogin(String login);
    Owner findByEmail(String email);
}
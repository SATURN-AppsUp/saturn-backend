package com.example.saturn.repositories;

import com.example.saturn.models.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends MongoRepository<Account,String> {
    Optional<Account> findByEmail(String email);
}

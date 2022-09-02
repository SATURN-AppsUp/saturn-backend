package com.example.saturn.services;

import com.example.saturn.models.Account;
import com.example.saturn.models.enums.AccountStatus;
import com.example.saturn.models.requests.AccountCreateRequest;
import com.example.saturn.repositories.AccountRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@AllArgsConstructor
@Service
public class AccountService
{
    private final AccountRepo accountRepo;
    private final MongoTemplate template;

    private final GenIdService genIdService;


    public Account createAccount(AccountCreateRequest request) {
        var acc = new Account(
                genIdService.genNextId("account"),
                request.getUsername(),
                request.getPassword(),
                request.getName(),
                request.getDob(),
                request.getPhone(),
                request.getEmail(),
                AccountStatus.UNVERIFIED,
                request.isGender(),
                false,
                0,
                0,
                0,
                0
        );
        var query = new Query();
        query.addCriteria(where("email").is(acc.getEmail()));
        var existsAccountWithMail = template.exists(query, Account.class);
        var existsAccountWithUsername = template.exists(query(where("username").is(acc.getUsername())),Account.class);
        if (acc.getUsername().isEmpty())
        {
            throw new IllegalStateException("username must not be empty");
        }
        if (existsAccountWithUsername) {
            throw new IllegalStateException("username already existed");
        }
        if (existsAccountWithMail)
        {
            throw new IllegalStateException("email already existed");
        }
        if (acc.getPassword().isEmpty() || acc.getPassword().length() < 8) {
            throw new IllegalStateException("Password must contains at least 8 characters");
        }
        if (acc.getName().isEmpty())
        {
            throw new IllegalStateException("name can't be null");
        }
        return accountRepo.insert(acc);
    }
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }

    public List<Account> signIn(Account account) {
        var foundAccount = template.findOne(
                query(
                        where("email").is(account.getUsername())
                                .orOperator(
                                        where("username").is(account.getUsername()))),
                Account.class);
        if (!foundAccount.getUsername().isEmpty()) {
            return List.of(account);
        }
        return List.of();
    }

    public List getProfile(Account account) {
        var query = new Query();
        if (account.getUsername().length() > 0) {
            query.addCriteria(where("username").is(account.getUsername()));
        }

        if (account.getId() > 0 ) {
            query.addCriteria(where("id").is(account.getId()));
        }
        var result = template.findOne(query,Account.class);
        if (result.getPassword().length() > 0 ) {
            result.setPassword(null);
        }
        return List.of(result);
    }
}


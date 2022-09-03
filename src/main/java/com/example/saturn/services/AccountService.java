package com.example.saturn.services;

import com.example.saturn.config.ModelMapperConfig;
import com.example.saturn.models.Account;
import com.example.saturn.models.enums.AccountStatus;
import com.example.saturn.models.requests.AccountCreateRequest;
import com.example.saturn.models.requests.AccountRequest;
import com.example.saturn.models.responses.AccountProfileRepsonse;
import com.example.saturn.repositories.AccountRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@AllArgsConstructor
@Service
public class AccountService
{
    private final AccountRepo accountRepo;
    private final MongoTemplate template;

    private final GenIdService genIdService;

    @Autowired
    private final ModelMapper mapper;


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
        if (existsAccountWithUsername) {
            throw new IllegalArgumentException("username already existed");
        }
        if (existsAccountWithMail)
        {
            throw new IllegalArgumentException("email already existed");
        }
        if (acc.getPassword().isEmpty() || acc.getPassword().length() < 8) {
            throw new IllegalArgumentException("Password must contains at least 8 characters");
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

    public List getProfile(AccountRequest account) {
        var query = new Query();
        if (account.getUsername().length() > 0) {
            query.addCriteria(where("username").is(account.getUsername()));
        }

        if (account.getUsername().isEmpty() ) {
            throw new IllegalArgumentException("must provide username");
        }
        var result = template.findOne(query,Account.class);
        var resultDTO = mapper.map(result, AccountProfileRepsonse.class);
        if (result == null) {
            throw new NullPointerException("not found any matched account");
        }

        return List.of(result);
    }
}


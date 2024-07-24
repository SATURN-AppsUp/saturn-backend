package com.example.saturn.services;

import com.example.saturn.models.Account;
import com.example.saturn.models.Role;
import com.example.saturn.models.enums.AccountStatus;
import com.example.saturn.models.requests.AccountCreateRequest;
import com.example.saturn.models.requests.AccountRequest;
import com.example.saturn.models.responses.AccountProfileRepsonse;
import com.example.saturn.repositories.AccountRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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


    public AccountProfileRepsonse createAccount(AccountCreateRequest request) {
        var acc = new Account(
                genIdService.genNextId("account"),
                request.getUsername(),
                request.getPassword(),
                request.getName(),
                request.getDob(),
                request.getPhone(),
                request.getEmail(),
                AccountStatus.UNVERIFIED,
                request.getGender(),
                false,
                0,
                0,
                0,
                0,
                request.getAddressList(),
                Role.USER
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
        var account = accountRepo.insert(acc);
        var resultDTO = mapper.map(account, AccountProfileRepsonse.class);
        return resultDTO;
    }
    public List<AccountProfileRepsonse> getAccounts(AccountRequest request) {
        var query = new Query();
        List<Account> result;
        if (request.getUsername() != null && request.getUsername().length() > 0){
            query.addCriteria(where("username").is(request.getUsername()));
        }

        if (request.getName() != null && request.getName().length() > 0){
            query.addCriteria(where("name").is(request.getName()));
        }

        if (request.getPhone() != null && request.getPhone().length() >0) {
            query.addCriteria(where("phone").is(request.getPhone()));
        }

        if (request.getStatus() != null) {
            var accountStatus = AccountStatus.valueOf(request.getStatus());
            query.addCriteria(where("status").is(accountStatus));
        }

        if (request.isSeller()) {
            query.addCriteria(where("isSeller").is(request.isSeller()));
        }


        if (request.getDeliveredOrders() >=0) {
            query.addCriteria(where("deliveredOrders").is(request.getDeliveredOrders()));
        }

        if (request.getProcessingOrders() >=0) {
            query.addCriteria(where("processingOrders").is(request.getProcessingOrders()));
        }

        if (request.getWaitToConfirmedOrders() >=0) {
            query.addCriteria(where("waitToConfirmedOrders").is(request.getWaitToConfirmedOrders()));
        }

        if (request.getWaitToConfirmedOrders() >=0) {
            query.addCriteria(where("points").is(request.getPoints()));
        }

        if (query.getQueryObject().size() ==0){
            result = template.findAll(Account.class);
        }
        else result = template.find(query,Account.class);

        List<AccountProfileRepsonse> listAccountProfileResponse = result
                .stream()
                .map(source -> mapper.map(source, AccountProfileRepsonse.class))
                .collect(Collectors.toList());

        return listAccountProfileResponse;
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


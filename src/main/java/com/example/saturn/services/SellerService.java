package com.example.saturn.services;


import com.example.saturn.models.Account;
import com.example.saturn.models.Seller;
import com.example.saturn.models.enums.SellerStatus;
import com.example.saturn.models.requests.SellerCreateRequest;
import com.example.saturn.models.requests.SellerUpdateRequest;
import com.example.saturn.repositories.SellerRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@AllArgsConstructor
@Service
public class SellerService {

    private final SellerRepo sellerRepo;
    private final MongoTemplate template;

    private final UtilService utilService;
    private final GenIdService genIdService;

    public List<Seller> updateSeller(SellerUpdateRequest seller) {
        var query = new Query();
        query.addCriteria(where("id").is(seller.getId()).orOperator(where("seller_code").is(seller.getSellerCode())));
        var result = template.findOne(query,Seller.class);
        if (result == null) {
            throw new NullPointerException("not found any matched seller");
        }
        result.setSellerAdress(seller.getSellerAdress());
        result.setStatus(seller.getStatus());
        result.setSellerName(seller.getSellerName());

        var updateResult = sellerRepo.save(result);
        return List.of(updateResult);
    }
    public List<Seller> getSeller(Seller seller) {
        var query = new Query();
        var result = template.findOne(query,Seller.class);
        return List.of(result);
    }
    public Seller createSeller(SellerCreateRequest seller) {
        var query = new Query();
        var userWithUsername = template.findOne(query(where("username").is(seller.getUsername())), Account.class);

        if (userWithUsername == null || userWithUsername.getId() == 0) {
            throw new IllegalArgumentException("not found account with this username");
        }
        query.addCriteria(where("userId").is(userWithUsername.getId()));
        var existsAccountWithUser = template.findOne(query, Seller.class);

        if (existsAccountWithUser != null) {
            throw new IllegalArgumentException("another seller with this user has already existed");
        }
        String sellerCode = "";
        boolean existsSellerWithCode = true;
        int maxRetry = 5;
        int retry = 0;
        while (existsSellerWithCode || retry <= maxRetry) {
            sellerCode = utilService.genRandomCode(10);
            existsSellerWithCode = template.exists(query(where("seller_code").is(sellerCode)), Seller.class);
            retry++;
        }

        if (existsSellerWithCode) {
            throw new IllegalArgumentException("another seller with this seller code has already existed");
        }

        if (seller.getAcceptedPaymentMethods().size() == 0)
        {
            throw new IllegalArgumentException("Seller must have at lease one payment method");
        }

        var newSeller = new Seller(
                userWithUsername.getId(),
                genIdService.genNextId("seller"),
                sellerCode,
                seller.getSellerName(),
                false,
                seller.getSellerAdress(),
                seller.getAcceptedPaymentMethods(),
                SellerStatus.ACTIVATED
                );
        return sellerRepo.insert(newSeller);
    }
}

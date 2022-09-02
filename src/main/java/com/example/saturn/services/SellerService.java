package com.example.saturn.services;


import com.example.saturn.models.Seller;
import com.example.saturn.models.enums.SellerStatus;
import com.example.saturn.models.requests.SellerCreateRequest;
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

    public List<Seller> getSeller(Seller seller) {
        var query = new Query();
        var result = template.findOne(query,Seller.class);
        return List.of(result);
    }
    public Seller createSeller(SellerCreateRequest seller) {
        var query = new Query();
        query.addCriteria(where("userId").is(seller.getUserId()));
        var existsAccountWithUser = template.exists(query, Seller.class);
        if (existsAccountWithUser) {
            throw new IllegalArgumentException("another seller with this user has already existed");
        }
        String sellerCode = "";
        boolean existsSellerWithCode = true;
        int maxRetry = 5;
        int retry = 0;
        while (existsSellerWithCode || retry <= maxRetry) {
            sellerCode = utilService.genRandomCode(10);
            existsSellerWithCode = template.exists(query(where("sellerCode").is(sellerCode)), Seller.class);
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
                seller.getUserId(),
                genIdService.genNextId("seller"),
                sellerCode,
                seller.getSellerName(),
                0,
                false,
                seller.getSellAdress(),
                seller.getAcceptedPaymentMethods(),
                SellerStatus.ACTIVATED
                );
        return sellerRepo.insert(newSeller);
    }
}

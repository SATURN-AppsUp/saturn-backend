package com.example.saturn.services;


import com.example.saturn.models.Seller;
import com.example.saturn.models.Store;
import com.example.saturn.models.enums.StoreStatus;
import com.example.saturn.models.requests.StoreCreateRequest;
import com.example.saturn.models.requests.StoreRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@AllArgsConstructor
@Service
public class StoreService {

    private final MongoTemplate template;
    private final UtilService utilService;

    private final GenIdService genIdService;

    public List<Store> createStore(StoreCreateRequest request) {
        var isStoreExisted = template.exists(query(where("sellerId").is(request.getSellerId())),Store.class);
        var storeCode = "";
        System.out.println(isStoreExisted + " " + request.getSellerId());

        if (isStoreExisted) {
            throw new IllegalArgumentException("Store with this seller already existed");
        }
        else {
            var sellerWithRequestId = template.findOne(query(where("id").is(request.getSellerId())), Seller.class);
            if (sellerWithRequestId == null) {
                throw new NullPointerException("not found any matched seller");
            }
            storeCode = utilService.genRandomCode(10);
            var existStoreWithCode = template.exists(query(where("storeCode").is(storeCode)),Store.class);
            if (existStoreWithCode) {
                storeCode = utilService.genRandomCode(10);
            }
            var newStore = new Store(
                    sellerWithRequestId.getId(),
                    sellerWithRequestId.getSellerCode(),
                    genIdService.genNextId("store"),
                    request.getStoreName(),storeCode,
                    StoreStatus.DRAFT,"");
            var createdStore = template.insert(newStore);
            return List.of(createdStore);
        }
    }

    public List<Store> getStore(StoreRequest request) {
        var query = new Query();
        if (request.getStoreCode() != null && request.getStoreCode().length() > 0 ) {
            query.addCriteria(where("storeCode").is(request.getStoreCode()));
        }

        if (request.getSellerCode() != null && request.getSellerCode().length() > 0) {
            query.addCriteria(where("sellerCode").is(request.getSellerCode()));
        }

        if (request.getStoreName() != null && request.getStoreCode().length() > 0) {
            query.addCriteria(where("storeName").is(request.getStoreName()));
        }

        System.out.println(query.getQueryObject().size());
        if (query.getQueryObject().size() == 0 ) {
            return template.findAll(Store.class);
        }
        return template.find(query,Store.class);
    }
}

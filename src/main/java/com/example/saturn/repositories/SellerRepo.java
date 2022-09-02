package com.example.saturn.repositories;

import com.example.saturn.models.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepo extends MongoRepository<Seller,String> {

}

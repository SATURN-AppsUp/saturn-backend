package com.example.saturn.listeners;

import com.example.saturn.models.AmazonProduct;
import com.example.saturn.models.SKU;
import com.example.saturn.models.requests.AccountCreateRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AccountRegistrationListener {
//    @RabbitListener(queues = {"q.account-registration"})
//    public void onAccountRegistration(AccountCreateRequest event) {
//        log.info("Account registration event received: {}", event);
//    }

    MongoTemplate template;
    @RabbitListener(queues = {"q.amazon-product-create"})
    public void onAmazonProductCreate(AmazonProduct product) {
        log.info("Amazon product create event received: {}", product.getTitle());
        template.save(product);
    }
}

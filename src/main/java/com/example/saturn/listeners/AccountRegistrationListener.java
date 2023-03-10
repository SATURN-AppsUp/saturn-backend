package com.example.saturn.listeners;

import com.example.saturn.models.SKU;
import com.example.saturn.models.requests.AccountCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountRegistrationListener {
//    @RabbitListener(queues = {"q.account-registration"})
//    public void onAccountRegistration(AccountCreateRequest event) {
//        log.info("Account registration event received: {}", event);
//    }

    @RabbitListener(queues = {"q.amazon-product-create"})
    public void onAmazonProductCreate(SKU sku) {
        log.info("Amazon product create event received: {}", sku);
    }
}

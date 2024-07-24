package com.example.saturn.services.Job;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SaleOrderProcessingService {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void queueSaleOrder(String order) {
        executorService.execute(new SaleOrderProcessHandler(order));
    }
}

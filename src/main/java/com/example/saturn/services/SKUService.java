package com.example.saturn.services;


import com.example.saturn.models.SKU;
import com.example.saturn.models.enums.SKUStatus;
import com.example.saturn.models.requests.SKUCreateRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SKUService {

    private final MongoTemplate template;
    private final GenIdService genIdService;

    public SKU createSKU(SKUCreateRequest sku) {

        if (sku.getBrand() != null || !sku.getCategoryCode().isBlank()) {

        }

        if (sku.getIsActive() == null){
            sku.setIsActive(false);
        }
        var skuStatus = sku.getIsActive() == true ? SKUStatus.SELLING : SKUStatus.DRAFT;
        var createdSKU = new SKU(
                genIdService.genNextId("sku"),
                sku.getSku(),sku.getSellerCode(),sku.getBrand(),sku.getName(),sku.getPackaging(),
                sku.getCategoryCode(),
                "TEMP_CATEGORY_NAME",sku.getProductType(),
                sku.getSaleType(),
                sku.getVarietyList(),
                sku.getUnitOfMeasure(),
                sku.getIsActive(),
                sku.getMaximumOrderQuantity(),
                skuStatus,
                sku.getAvailableQuantity(),
                0,
                sku.getExpiryDate()
                );
        return template.insert(createdSKU);
    }

}

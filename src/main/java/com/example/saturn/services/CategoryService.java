package com.example.saturn.services;

import com.example.saturn.models.Category;
import com.example.saturn.models.requests.CategoryRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@AllArgsConstructor
public class CategoryService {

    private final MongoTemplate template;
    private final GenIdService service;
    public List<Category> getCategory(CategoryRequest request) {
        var query = new Query();
        if (!request.getCategoryCode().isEmpty()) {
            query.addCriteria(where("categoryCode").is(request.getCategoryCode()));
        }

        if (!request.getCategoryName().isEmpty()) {
            query.addCriteria(where("categoryName").is(request.getCategoryName()));
        }
        return template.find(query, Category.class);
    }

    public Category createCategory(String categoryName) {
        String categoryCode = "";
        if (categoryName.isEmpty()) {
            throw new IllegalArgumentException("category name must not be empty");
        } else
        {
            categoryCode = "CAT_" + service.getCurrentId("category");
        }
        var createCategory = new Category(service.genNextId("category"), categoryCode,categoryName);
        return template.insert(createCategory);
    }

    public Category createCategory(String categoryName, String categoryCode) {
        if (categoryName.isEmpty()) {
            throw new IllegalArgumentException("category name must not be empty");
        } else  if (categoryCode.isEmpty())
        {
            categoryCode = "CAT_" + service.getCurrentId("category");
        }
        var createCategory = new Category(service.genNextId("category"), categoryCode,categoryName);
        return template.insert(createCategory);
    }

    public Category createCategory(CategoryRequest request) {
        assert request != null;
        if (request.getCategoryName().isEmpty()) {
            throw new IllegalArgumentException("category name must not be empty");
        } else {
            if (request.getCategoryCode().isEmpty())
            {
                request.setCategoryCode(request.getCategoryName().toUpperCase());
            }
        }
        var createCategory = new Category(service.genNextId("category"), request.getCategoryCode(),request.getCategoryName());
        return template.insert(createCategory);
    }
}

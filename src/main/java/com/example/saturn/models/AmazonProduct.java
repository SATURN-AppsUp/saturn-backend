package com.example.saturn.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("amazonProduct")
public class AmazonProduct {
    private List<String> category;
    private String tech1;
    private List<String> description;
    private String fit;
    private String title;
    private List<String> also_buy;
    private String tech2;
    private String brand;
    private List<String> feature;
    private List<String> rank;
    private List<String> also_view;
    private String main_cat;
    private String similar_item;
    private String date;
    private String price;
    private String asin;
    private List<String> imageURL;
    private List<String> imageURLHighRes;
}

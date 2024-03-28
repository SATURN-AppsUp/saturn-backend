package com.example.saturn.models.requests;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.mongodb.lang.Nullable;
import lombok.Data;

@Data
public class CategoryRequest {
    @Nullable
    @JsonSetter(nulls = Nulls.SKIP)
    private String categoryCode="";
    @Nullable
    @JsonSetter(nulls = Nulls.SKIP)
    private String categoryName;
}

package com.example.saturn.models.requests;

import com.example.saturn.models.Category;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.mongodb.lang.Nullable;
import lombok.Data;

@Data
public class SKUOptionRequest {
    @Nullable
    @JsonSetter(nulls = Nulls.SKIP)
    private boolean getCategory=false;
    @Nullable
    private boolean getBrand=false;
}

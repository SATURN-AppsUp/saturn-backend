package com.example.saturn.models.dao;

import com.example.saturn.models.SKUVariety;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class VarietyDao implements  BaseDao<SKUVariety> {
    @Override
    public Optional<SKUVariety> get(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<SKUVariety> getAll() {
        return null;
    }

    @Override
    public int save(SKUVariety skuVariety) {
        return 0;
    }

    @Override
    public void update(SKUVariety skuVariety) {

    }

    @Override
    public void delete(SKUVariety skuVariety) {

    }

    @Override
    public boolean isValid(SKUVariety skuVariety) {
        return skuVariety.getUnitPrice() >=0 && skuVariety.getAvailableQuantity() >=0;

    }
}

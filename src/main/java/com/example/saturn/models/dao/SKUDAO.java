package com.example.saturn.models.dao;

import com.example.saturn.models.SKU;

import java.util.Collection;
import java.util.Optional;

public class SKUDAO implements  BaseDao<SKU>{

    @Override
    public Optional<SKU> get(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<SKU> getAll() {
        return null;
    }

    @Override
    public int save(SKU sku) {
        return 0;
    }

    @Override
    public void update(SKU sku) {

    }

    @Override
    public void delete(SKU sku) {

    }

    @Override
    public boolean isValid(SKU sku) {
        return false;
    }
}

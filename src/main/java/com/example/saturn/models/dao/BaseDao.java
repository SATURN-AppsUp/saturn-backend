package com.example.saturn.models.dao;

import java.util.Collection;
import java.util.Optional;

public interface BaseDao<T> {

    Optional<T> get(int id);
    Collection<T> getAll();
    int save(T t);
    void update(T t);
    void delete(T t);
    boolean isValid(T t);
}


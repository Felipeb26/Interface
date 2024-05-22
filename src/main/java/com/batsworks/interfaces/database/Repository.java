package com.batsworks.interfaces.database;

import java.util.List;

public interface Repository<T> {

    List<T> findAll();

    T findById(Long id);

    Boolean save(T t);

    Boolean update(Long id, T t);

    Boolean delete(Long id);

    T custom(String query);
}

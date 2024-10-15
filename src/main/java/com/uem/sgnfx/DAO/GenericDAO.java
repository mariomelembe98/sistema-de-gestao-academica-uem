package com.uem.sgnfx.DAO;

import java.util.List;

public interface GenericDAO<T> {
    void create(T entity);
    List<T> readAll();
    void update(T entity);
    void delete(Long id);
}

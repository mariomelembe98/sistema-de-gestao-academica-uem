package com.uem.sgnfx.DAO;


import java.util.List;

public interface GenericDAO<T> {

    T create(T entity);
    List<T> readAll();
    T read(Long id);
    void update(T entity);
    void delete(Long id);
    long count();
}

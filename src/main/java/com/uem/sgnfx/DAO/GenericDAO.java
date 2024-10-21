package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.Inscricao;

import java.util.List;

public interface GenericDAO<T> {
    void create(T entity);
    List<T> readAll();
    void read(Long id);
    void update(T entity);
    void delete(Long id);
}

package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.Estudante;
import java.util.List;

public interface EstudanteDAO {
    void create(Estudante estudante);
    Estudante read(Long id);
    List<Estudante> readAll();
    void update(Estudante estudante);
    void delete(Long id);
}


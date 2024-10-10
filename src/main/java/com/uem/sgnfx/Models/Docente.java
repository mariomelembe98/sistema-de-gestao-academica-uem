package com.uem.sgnfx.Models;

import java.util.Date;
import java.util.List;

/**
 * Created by USER on 21/09/2024.
 */

public class Docente extends Pessoa{

    private List<Disciplina> disciplinas;

    public Docente() {
    }

    public Docente(long id, String nome, String apelido, String email, String telefone, Date dataNascimento, boolean isStatus, boolean isAdmin, String senha) {
        super(id, nome, apelido, email, telefone, dataNascimento, isStatus, isAdmin, senha);
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}

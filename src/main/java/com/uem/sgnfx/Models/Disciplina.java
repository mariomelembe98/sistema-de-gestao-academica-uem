package com.uem.sgnfx.Models;

import java.util.List;

/**
 * Created by USER on 07/10/2024.
 */

public class Disciplina {
    private Long id;
    private String nome;
    private Curso curso;
    private List<Docente> docenteResponsavels;
    private List<Estudante> estudantesMatriculados;

    public Disciplina(Long id, String nome, Curso curso, List<Docente> docenteResponsavels, List<Estudante> estudantesMatriculados) {
        this.id = id;
        this.nome = nome;
        this.curso = curso;
        this.docenteResponsavels = docenteResponsavels;
        this.estudantesMatriculados = estudantesMatriculados;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Docente> getDocenteResponsavels() {
        return docenteResponsavels;
    }

    public void setDocenteResponsavels(List<Docente> docenteResponsavels) {
        this.docenteResponsavels = docenteResponsavels;
    }

    public List<Estudante> getEstudantesMatriculados() {
        return estudantesMatriculados;
    }

    public void setEstudantesMatriculados(List<Estudante> estudantesMatriculados) {
        this.estudantesMatriculados = estudantesMatriculados;
    }
}

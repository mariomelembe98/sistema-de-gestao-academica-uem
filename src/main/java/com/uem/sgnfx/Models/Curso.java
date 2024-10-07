package com.uem.sgnfx.Models;

import java.util.List;

/**
 * Created by USER on 07/10/2024.
 */

public class Curso {

    private Long id;
    private String nome;
    private Curso curso;
    private Docente docenteResponsavel;
    private List<Estudante> estudantesMatriculados;

    public Curso(Long id, String nome, Curso curso, Docente docenteResponsavel, List<Estudante> estudantesMatriculados) {
        this.id = id;
        this.nome = nome;
        this.curso = curso;
        this.docenteResponsavel = docenteResponsavel;
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

    public Docente getDocenteResponsavel() {
        return docenteResponsavel;
    }

    public void setDocenteResponsavel(Docente docenteResponsavel) {
        this.docenteResponsavel = docenteResponsavel;
    }

    public List<Estudante> getEstudantesMatriculados() {
        return estudantesMatriculados;
    }

    public void setEstudantesMatriculados(List<Estudante> estudantesMatriculados) {
        this.estudantesMatriculados = estudantesMatriculados;
    }
}

package com.uem.sgnfx.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "disciplinas", schema = "gestao_academica")
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "designacao", nullable = false)
    private String designacao;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToMany(mappedBy = "disciplina")
    private Set<DisciplinaDocente> disciplinaDocentes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "disciplina")
    private Set<DisciplinaEstudante> disciplinaEstudantes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "disciplina")
    private Set<Inscricao> inscricoes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "disciplina")
    private Set<Nota> notas = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public Curso getCurso() {
        return curso;
    }

    public String getCursoNome() {
        return curso != null ? curso.getNome() : "Sem curso";
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<DisciplinaDocente> getDisciplinaDocentes() {
        return disciplinaDocentes;
    }

    public void setDisciplinaDocentes(Set<DisciplinaDocente> disciplinaDocentes) {
        this.disciplinaDocentes = disciplinaDocentes;
    }

    public Set<DisciplinaEstudante> getDisciplinaEstudantes() {
        return disciplinaEstudantes;
    }

    public void setDisciplinaEstudantes(Set<DisciplinaEstudante> disciplinaEstudantes) {
        this.disciplinaEstudantes = disciplinaEstudantes;
    }

    public Set<Inscricao> getInscricoes() {
        return inscricoes;
    }

    public void setInscricoes(Set<Inscricao> inscricoes) {
        this.inscricoes = inscricoes;
    }

    public Set<Nota> getNotas() {
        return notas;
    }

    public void setNotas(Set<Nota> notas) {
        this.notas = notas;
    }

}
package com.uem.sgnfx.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "Disciplina")
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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "semestre_id", nullable = false)
    private Semestre semestre;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToMany(mappedBy = "disciplina")
    private Set<Avaliacao> avaliacaos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "disciplina")
    private Set<DisciplinaDocente> disciplinaDocentes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "disciplina")
    private Set<Inscricao> inscricaos = new LinkedHashSet<>();

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

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getCursoNome() {
        return curso != null ? curso.getNome() : "Sem curso";
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
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

    public Set<Avaliacao> getAvaliacaos() {
        return avaliacaos;
    }

    public void setAvaliacaos(Set<Avaliacao> avaliacaos) {
        this.avaliacaos = avaliacaos;
    }

    public Set<DisciplinaDocente> getDisciplinaDocentes() {
        return disciplinaDocentes;
    }

    public void setDisciplinaDocentes(Set<DisciplinaDocente> disciplinaDocentes) {
        this.disciplinaDocentes = disciplinaDocentes;
    }

    public Set<Inscricao> getInscricaos() {
        return inscricaos;
    }

    public void setInscricaos(Set<Inscricao> inscricaos) {
        this.inscricaos = inscricaos;
    }

}
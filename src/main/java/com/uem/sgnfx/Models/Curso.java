package com.uem.sgnfx.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "cursos", schema = "gestao_academica")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Lob
    @Column(name = "descricao")
    private String descricao;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToMany(mappedBy = "curso")
    private Set<Disciplina> disciplinas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "curso")
    private Set<Estudante> estudantes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "curso")
    private Set<Turma> turmas = new LinkedHashSet<>();

    public Curso() {}

    public Curso(String nome, String descricao, Departamento departamento, Instant createdAt, Instant updatedAt) {
        this.nome = nome;
        this.descricao = descricao;
        this.departamento = departamento;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getDepartamentoNome() {
        return departamento != null ? departamento.getNome() : "Sem departamento";
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

    public Set<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Set<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public Set<Estudante> getEstudantes() {
        return estudantes;
    }

    public void setEstudantes(Set<Estudante> estudantes) {
        this.estudantes = estudantes;
    }

    public Set<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(Set<Turma> turmas) {
        this.turmas = turmas;
    }

}
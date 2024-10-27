package com.uem.sgnfx.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "User")
@Table(name = "users", schema = "gestao_academica", uniqueConstraints = {
        @UniqueConstraint(name = "users_email_unique", columnNames = {"email"})
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "apelido", nullable = false)
    private String apelido;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "genero", nullable = false)
    private String genero;

    @ColumnDefault("0")
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    @ColumnDefault("0")
    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin = false;

    @Column(name = "email_verified_at")
    private Instant emailVerifiedAt;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "remember_token", length = 100)
    private String rememberToken;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @OneToMany(mappedBy = "createdBy")
    private Set<Avaliacao> avaliacaos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Docente> docentes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Estudante> estudantes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "createdBy")
    private Set<Nota> notas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Pessoa> pessoas = new LinkedHashSet<>();

    public User(){}

    public User(String name, String apelido, String username, String email, String genero, Boolean isActive, Boolean isAdmin, String password, Instant createdAt, Instant updatedAt, Instant deletedAt) {
        this.name = name;
        this.apelido = apelido;
        this.username = username;
        this.email = email;
        this.genero = genero;
        this.isActive = isActive;
        this.isAdmin = isAdmin;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Instant getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(Instant emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
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

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Set<Avaliacao> getAvaliacaos() {
        return avaliacaos;
    }

    public void setAvaliacaos(Set<Avaliacao> avaliacaos) {
        this.avaliacaos = avaliacaos;
    }

    public Set<Docente> getDocentes() {
        return docentes;
    }

    public void setDocentes(Set<Docente> docentes) {
        this.docentes = docentes;
    }

    public Set<Estudante> getEstudantes() {
        return estudantes;
    }

    public void setEstudantes(Set<Estudante> estudantes) {
        this.estudantes = estudantes;
    }

    public Set<Nota> getNotas() {
        return notas;
    }

    public void setNotas(Set<Nota> notas) {
        this.notas = notas;
    }

    public Set<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(Set<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

}
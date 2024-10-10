package com.uem.sgnfx.Models;

import java.util.Date;

/**
 * Created by USER on 21/09/2024.
 */

public abstract class Pessoa {
    private long id;
    private String nome;
    private String apelido;
    private String email;
    private String telefone;
    private Date dataNascimento;
    private boolean isStatus = true;
    private boolean isAdmin = false;
    private String senha;

    public Pessoa() {}

    public Pessoa(long id, String nome, String apelido, String email, String telefone, Date dataNascimento, boolean isStatus, boolean isAdmin, String senha) {
        this.id = id;
        this.nome = nome;
        this.apelido = apelido;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.isStatus = isStatus;
        this.isAdmin = isAdmin;
        this.senha = senha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public boolean isStatus() {
        return isStatus;
    }

    public void setStatus(boolean status) {
        isStatus = status;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", apelido='" + apelido + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", isStatus=" + isStatus +
                ", isAdmin=" + isAdmin +
                ", senha='" + senha + '\'' +
                '}';
    }
}

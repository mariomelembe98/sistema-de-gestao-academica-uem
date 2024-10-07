package com.uem.sgnfx.Models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by USER on 21/09/2024.
 */

@Entity
@Table(name = "db_sgn")
public class Estudante extends Pessoa{

    /**
     * @param id
     * @param nome
     * @param apelido
     * @param email
     * @param telefone
     * @param dataNascimento
     * @param isStatus
     * @param isAdmin
     * @param senha
     */

    public Estudante(long id, String nome, String apelido, String email, String telefone, Date dataNascimento, boolean isStatus, boolean isAdmin, String senha) {
        super(id, nome, apelido, email, telefone, dataNascimento, isStatus, isAdmin, senha);
    }

    public Estudante() {}

    /**
     * @return
     */
    @Override
    public long getId() {
        return super.getId();
    }

    /**
     * @param id
     */
    @Override
    public void setId(long id) {
        super.setId(id);
    }

    /**
     * @return
     */
    @Override
    public String getNome() {
        return super.getNome();
    }

    /**
     * @param nome
     */
    @Override
    public void setNome(String nome) {
        super.setNome(nome);
    }

    /**
     * @return
     */
    @Override
    public String getApelido() {
        return super.getApelido();
    }

    /**
     * @param apelido
     */
    @Override
    public void setApelido(String apelido) {
        super.setApelido(apelido);
    }

    /**
     * @return
     */
    @Override
    public String getEmail() {
        return super.getEmail();
    }

    /**
     * @param email
     */
    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    /**
     * @return
     */
    @Override
    public String getTelefone() {
        return super.getTelefone();
    }

    /**
     * @param telefone
     */
    @Override
    public void setTelefone(String telefone) {
        super.setTelefone(telefone);
    }

    /**
     * @return
     */
    @Override
    public Date getDataNascimento() {
        return super.getDataNascimento();
    }

    /**
     * @param dataNascimento
     */
    @Override
    public void setDataNascimento(Date dataNascimento) {
        super.setDataNascimento(dataNascimento);
    }

    /**
     * @return
     */
    @Override
    public boolean isStatus() {
        return super.isStatus();
    }

    /**
     * @param status
     */
    @Override
    public void setStatus(boolean status) {
        super.setStatus(status);
    }
}

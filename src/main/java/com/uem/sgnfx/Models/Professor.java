package com.uem.sgnfx.Models;

import java.util.Date;

/**
 * Created by USER on 21/09/2024.
 */

public class Professor extends Pessoa{

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
    public Professor(long id, String nome, String apelido, String email, String telefone, Date dataNascimento, boolean isStatus, boolean isAdmin, String senha) {
        super(id, nome, apelido, email, telefone, dataNascimento, isStatus, isAdmin, senha);
    }
}

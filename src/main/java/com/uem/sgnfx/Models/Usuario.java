package com.uem.sgnfx.Models;

import java.util.Date;

/**
 * Created by USER on 21/09/2024.
 */

public class Usuario extends Pessoa{

    public Usuario(long id, String nome, String apelido, String email, String telefone, Date dataNascimento, boolean isStatus, boolean isAdmin, String senha) {
        super(id, nome, apelido, email, telefone, dataNascimento, isStatus, isAdmin, senha);
    }

    public Usuario() {}



}

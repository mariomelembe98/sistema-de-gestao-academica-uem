package com.uem.sgnfx.Models;

import java.util.Date;

/**
 * Created by USER on 07/10/2024.
 */

public class Nota {

    private Long id;
    private Estudante estudante;
    private Disciplina disciplina;
    private double valor;
    private Date dataLancamento;

    public Nota(Long id, Estudante estudante, Disciplina disciplina, double valor, Date dataLancamento) {
        this.id = id;
        this.estudante = estudante;
        this.disciplina = disciplina;
        this.valor = valor;
        this.dataLancamento = dataLancamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
}

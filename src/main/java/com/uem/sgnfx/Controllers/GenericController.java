package com.uem.sgnfx.Controllers;

/**
 * Created by USER on 25/10/2024.
 */

import com.uem.sgnfx.DAO.GenericDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import java.util.List;

public abstract class GenericController<T> {

    protected GenericDAO<T> dao;
    protected TableView<T> tableView;

    // Construtor que recebe o DAO genérico e a tabela associada ao controlador
    public GenericController(GenericDAO<T> dao, TableView<T> tableView) {
        this.dao = dao;
        this.tableView = tableView;
    }

    // Método genérico para carregar todos os dados na tabela
    public void carregarDados() {
        List<T> lista = dao.readAll();
        ObservableList<T> observableList = FXCollections.observableArrayList(lista);
        tableView.setItems(observableList);
    }

    // Método genérico para adicionar uma nova entidade
    public void adicionar(T entity) {
        dao.create(entity);
        carregarDados();
    }

    // Método genérico para atualizar uma entidade
    public void atualizar(T entity) {
        dao.update(entity);
        carregarDados();
    }

    // Método genérico para excluir uma entidade pelo ‘ID’
    public void excluir(Long id) {
        dao.delete(id);
        carregarDados();
    }

    // Método para configurar ComboBox com valores de entidades
    public void configurarComboBox(ComboBox<T> comboBox, List<T> lista) {
        ObservableList<T> observableList = FXCollections.observableArrayList(lista);
        comboBox.setItems(observableList);
    }
}


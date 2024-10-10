package com.uem.sgnfx.Controllers.Professor;

/**
 * Created by USER on 19/09/2024.
 */

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ProfessorController {
    @FXML
    private JFXButton btnDisciplinas;

    @FXML
    private JFXButton btnHome;

    @FXML
    private Tab tabDisciplinas;

    @FXML
    private Tab tabHome;

    @FXML
    private TabPane tabPane;

    @FXML
    void initialize() {


        btnDisciplinas.setOnAction(event -> tabPane.getSelectionModel().select(tabDisciplinas));

    }

}

package com.uem.sgnfx.Controllers.Professor;

/**
 * Created by USER on 19/09/2024.
 */

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;

public class ProfessorController {

    @FXML
    private ImageView arrowDisciplina;

    @FXML
    private ImageView arrowDisciplinas;

    @FXML
    private ImageView arrowTurmas;

    @FXML
    private ImageView arrowTurmas1;

    @FXML
    private JFXButton btnDisciplina;

    @FXML
    private JFXButton btnDisciplinas;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnTurmas;

    @FXML
    private Label panelDisciplinas;

    @FXML
    private Label panelDisciplinas1;

    @FXML
    private Label panelTurmas;

    @FXML
    private Label panelTurmas1;

    @FXML
    private Tab tabDisciplina;

    @FXML
    private Tab tabDisciplinas;

    @FXML
    private Tab tabHome;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabTurmas;

    @FXML
    private Tab tabTurmas1;

    @FXML
    void initialize() {
        btnDisciplinas.setOnAction(event -> tabPane.getSelectionModel().select(tabDisciplinas));
        panelDisciplinas.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabHome));
        arrowDisciplinas.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabHome));
        btnTurmas.setOnAction(event -> tabPane.getSelectionModel().select(tabTurmas));
        panelTurmas.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabHome));
        arrowTurmas.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabHome));
        btnDisciplina.setOnAction(event -> tabPane.getSelectionModel().select(tabDisciplina));
        arrowDisciplina.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabDisciplinas));

    }

}

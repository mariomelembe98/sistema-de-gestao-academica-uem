package com.uem.sgnfx.Controllers.Admin;

/**
 * Created by USER on 19/09/2024.
 */


import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class AdminController {

    @FXML
    private Button btnAdicionarNovo;

    @FXML
    private Button btnDocentes;

    @FXML
    private Button btnEstudantes;

    @FXML
    private Button btnGestores;

    @FXML
    private JFXButton btnListarEstudantes;

    @FXML
    private JFXButton btnListarEstudantes1;

    @FXML
    private JFXButton btnRegistarEstudante;

    @FXML
    private JFXButton btnRegistarEstudante1;

    @FXML
    private ImageView iconVoltarPanelEstudantes;

    @FXML
    private ImageView iconVoltarPanelEstudantes1;

    @FXML
    private Label lblDocenteDashboard;

    @FXML
    private Label lblEstudanteDashboard;

    @FXML
    private Label lblHome;

    @FXML
    private Pane panelHomeEstudante;

    @FXML
    private Pane panelHomeEstudante1;

    @FXML
    private Pane panelHomeEstudante11;

    @FXML
    private Pane panelHomeEstudante2;

    @FXML
    private Tab tabAdicionarEstudantes;

    @FXML
    private Tab tabDocentes;

    @FXML
    private Tab tabEstudantes;

    @FXML
    private Tab tabGestores;

    @FXML
    private Tab tabHome;

    @FXML
    private Tab tabListarEstudantes;

    @FXML
    private TabPane tabPane;

    @FXML
    private TextField txtPesquisarEstudante;

    @FXML
    void initialize() {
        btnDocentes.setOnAction(event -> tabPane.getSelectionModel().select(tabDocentes));
        btnEstudantes.setOnAction(event -> tabPane.getSelectionModel().select(tabEstudantes));
        btnRegistarEstudante.setOnAction(event -> tabPane.getSelectionModel().select(tabAdicionarEstudantes));
        btnListarEstudantes.setOnAction(event -> tabPane.getSelectionModel().select(tabListarEstudantes));
        lblEstudanteDashboard.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabHome));
        lblDocenteDashboard.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabHome));
        btnAdicionarNovo.setOnAction(event -> tabPane.getSelectionModel().select(tabAdicionarEstudantes));
        btnGestores.setOnAction(event -> tabPane.getSelectionModel().select(tabGestores));
        iconVoltarPanelEstudantes.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabEstudantes));
        iconVoltarPanelEstudantes1.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabEstudantes));

    }

}

package com.uem.sgnfx.Controllers.Professor;

/**
 * Created by USER on 19/09/2024.
 */

import com.jfoenix.controls.JFXButton;
import com.uem.sgnfx.DAO.InscricaoDAOImpl;
import com.uem.sgnfx.Models.Docente;
import com.uem.sgnfx.Models.Inscricao;
import com.uem.sgnfx.Services.SessionManager;
import com.uem.sgnfx.Utils.HibernateUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.time.Instant;

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
    private Label lblLoggedUserName, panelDisciplinas;

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
    private TableView<Inscricao> inscricaoTableView;

    @FXML
    private TableColumn<Inscricao, String> inscricaoNumeroEstudanteColumn, inscricaoEstudanteColumn, inscricaoDisciplinaColumn;

    @FXML
    private TableColumn<Inscricao, Instant> inscricaoCreatedAtColumn, inscricaoUpdatedAtColumn;

    private InscricaoDAOImpl inscricaoDAO;

    @FXML
    void initialize() {

        this.inscricaoDAO = new InscricaoDAOImpl(HibernateUtil.getSessionFactory());

        Docente loggedInUser = SessionManager.getLoggedInEntity();

        if (loggedInUser != null) {
            lblLoggedUserName.setText("Bem-vindo, " + loggedInUser.getNome());
        }else {
            lblLoggedUserName.textProperty().setValue("Bem-vindo");
        }

        btnDisciplinas.setOnAction(event -> tabPane.getSelectionModel().select(tabDisciplinas));
        panelDisciplinas.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabHome));
        arrowDisciplinas.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabHome));
        btnTurmas.setOnAction(event -> tabPane.getSelectionModel().select(tabTurmas));
        panelTurmas.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabHome));
        arrowTurmas.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabHome));
        btnDisciplina.setOnAction(event -> tabPane.getSelectionModel().select(tabDisciplina));
        arrowDisciplina.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabDisciplinas));

        listarInscricoesNaTabela();
    }

    private void listarInscricoesNaTabela() {
        inscricaoNumeroEstudanteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Inscricao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Inscricao, String> cellData) {
                return new SimpleStringProperty(cellData.getValue().getEstudante().getCodigoestudante());
            }
        });
        inscricaoEstudanteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Inscricao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Inscricao, String> cellData) {
                return new SimpleStringProperty(cellData.getValue().getEstudante().getNome() + "   " + cellData.getValue().getEstudante().getApelido());
            }
        });
        inscricaoDisciplinaColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Inscricao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Inscricao, String> cellData) {
                return new SimpleStringProperty(cellData.getValue().getDisciplina().getCursoNome());
            }
        });
        inscricaoCreatedAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        inscricaoUpdatedAtColumn.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));

        carregarInscricoes();
    }

    private void carregarInscricoes() {
        ObservableList<Inscricao> inscricoes = FXCollections.observableArrayList(inscricaoDAO.readAll());
        inscricaoTableView.setItems(inscricoes);
    }


}

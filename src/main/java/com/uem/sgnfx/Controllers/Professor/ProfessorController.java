package com.uem.sgnfx.Controllers.Professor;

/**
 * Created by USER on 19/09/2024.
 */

import com.jfoenix.controls.JFXButton;
import com.uem.sgnfx.DAO.DisciplinaDAOImpl;
import com.uem.sgnfx.DAO.InscricaoDAOImpl;
import com.uem.sgnfx.Models.Disciplina;
import com.uem.sgnfx.Models.Docente;
import com.uem.sgnfx.Models.Inscricao;
import com.uem.sgnfx.Services.SessionManager;
import com.uem.sgnfx.Utils.HibernateUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Callback;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

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
    private AnchorPane disciplinasPane;

    @FXML
    private TableView<Inscricao> inscricaoTableView;

    @FXML
    private TableColumn<Inscricao, String> inscricaoNumeroEstudanteColumn, inscricaoEstudanteColumn, inscricaoDisciplinaColumn;

    @FXML
    private TableColumn<Inscricao, Instant> inscricaoCreatedAtColumn, inscricaoUpdatedAtColumn;

    private InscricaoDAOImpl inscricaoDAO;
    private DisciplinaDAOImpl disciplinaDAO;

    @FXML
    void initialize() {

        this.inscricaoDAO = new InscricaoDAOImpl(HibernateUtil.getSessionFactory());
        this.disciplinaDAO = new DisciplinaDAOImpl(HibernateUtil.getSessionFactory());

        Docente loggedInUser = SessionManager.getLoggedInEntity();

        if (loggedInUser != null) {
            lblLoggedUserName.setText("Bem-vindo, " + loggedInUser.getNome());
        }else {
            lblLoggedUserName.textProperty().setValue("Bem-vindo");
        }

        btnDisciplinas.setOnAction(event -> tabPane.getSelectionModel().select(tabDisciplinas));
        //panelDisciplinas.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabHome));
        //arrowDisciplinas.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabHome));
        btnTurmas.setOnAction(event -> tabPane.getSelectionModel().select(tabTurmas));
        panelTurmas.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabHome));
        arrowTurmas.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabHome));
        //btnDisciplina.setOnAction(event -> tabPane.getSelectionModel().select(tabDisciplina));
        arrowDisciplina.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabDisciplinas));

        listarInscricoesNaTabela();
        carregarDisciplinasDinamicamente();
    }

    private void listarInscricoesNaTabela() {
        inscricaoNumeroEstudanteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Inscricao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Inscricao, String> cellData) {
                return new SimpleStringProperty(cellData.getValue().getEstudante().getCodigoEstudante());
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

    public void carregarDisciplinasDinamicamente() {
        Docente docenteLogado = (Docente) SessionManager.getLoggedInEntity();

        if (docenteLogado != null) {
            // Supomos que existe um método no DAO que retorna as disciplinas associadas ao docente
            List<Disciplina> disciplinas = disciplinaDAO.getDisciplinasPorDocente(docenteLogado.getId());

            int xOffset = 50;  // Margem inicial à esquerda
            int yOffset = 50;  // Margem inicial superior

            for (Disciplina disciplina : disciplinas) {
                // Criar um botão para cada disciplina
                JFXButton btnDisciplina = new JFXButton();
                btnDisciplina.setPrefSize(130, 120);  // Definir tamanho
                btnDisciplina.setLayoutX(xOffset);  // Definir posição X
                btnDisciplina.setLayoutY(yOffset);  // Definir posição Y

                // Adicionar ícone no botão
                ImageView icon = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/icons8_book_96px.png"))));
                // Substitua pelo caminho correto
                icon.setFitHeight(120);
                icon.setFitWidth(130);
                btnDisciplina.setGraphic(icon);  // Adiciona o ícone ao botão
                btnDisciplina.setCursor(Cursor.HAND);

                // Ação ao clicar no botão
                btnDisciplina.setOnAction(event -> {
                    listarEstudantesPorDisciplina(disciplina);
                });

                // Criar um label com o nome da disciplina
                Label lblDisciplina = new Label(disciplina.getDesignacao());
                lblDisciplina.setFont(new Font("System Bold", 13));
                lblDisciplina.setLayoutX(xOffset);
                lblDisciplina.setLayoutY(yOffset + 130);  // Posicionado abaixo do botão

                // Adicionar o botão e o label ao AnchorPane
                disciplinasPane.getChildren().addAll(btnDisciplina, lblDisciplina);

                // Adiciona efeito de hover (alterando cor, borda, etc.)
                btnDisciplina.setOnMouseEntered(event -> {
                    btnDisciplina.setStyle("-fx-background-color: #E0E0E0;"); // cor de fundo ao passar o mouse
                });
                btnDisciplina.setOnMouseExited(event -> {
                    btnDisciplina.setStyle(""); // restaura o estilo original quando o mouse sai
                });

                // Atualizar a posição do próximo botão/label
                xOffset += 200;  // Espaçamento horizontal

                // Verificar se precisa mover para a próxima linha
                if (xOffset + 130 > disciplinasPane.getPrefWidth()) {
                    xOffset = 50;  // Resetar para o começo da linha
                    yOffset += 180;  // Mover para a próxima linha
                }
            }
        } else {
            System.out.println("Nenhum docente logado.");
        }
    }

    private void listarEstudantesPorDisciplina(Disciplina disciplina) {
        // Supomos que exista um método que retorne as inscrições de uma disciplina
        List<Inscricao> inscricoes = inscricaoDAO.getInscricoesPorDisciplina(disciplina.getId());

        ObservableList<Inscricao> inscricoesObservable = FXCollections.observableArrayList(inscricoes);
        inscricaoTableView.setItems(inscricoesObservable);  // Supondo que você tenha um TableView para exibir estudantes
    }


}

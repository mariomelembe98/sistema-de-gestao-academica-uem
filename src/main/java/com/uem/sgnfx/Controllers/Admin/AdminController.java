package com.uem.sgnfx.Controllers.Admin;

/**
 * Created by USER on 19/09/2024.
 */

import com.jfoenix.controls.JFXButton;
import com.uem.sgnfx.DAO.EstudanteDAOImpl;
import com.uem.sgnfx.DAO.UserDAOImpl;
import com.uem.sgnfx.Models.Estudante;
import com.uem.sgnfx.Models.User;
import com.uem.sgnfx.Utils.HibernateUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.util.List;

public class AdminController {

    @FXML
    private Button btnAddDocente;

    @FXML
    private Button btnAddEstudante;

    @FXML
    private Button btnBuscaDocente;

    @FXML
    private Button btnBuscarEstudantes;

    @FXML
    private Button btnCursos;

    @FXML
    private JFXButton btnDisciplinas;

    @FXML
    private Button btnDocentes;

    @FXML
    private Button btnEstudantes;

    @FXML
    private JFXButton btnListarCursos;

    @FXML
    private JFXButton btnListarDisciplinas;

    @FXML
    private JFXButton btnListarDocentes;

    @FXML
    private JFXButton btnListarEstudantes;

    @FXML
    private JFXButton btnListarTurmas;

    @FXML
    private JFXButton btnListarUtilizadores;

    @FXML
    private JFXButton btnRegistarCurso;

    @FXML
    private JFXButton btnRegistarDisciplina;

    @FXML
    private Button btnRegistarDocente;

    @FXML
    private Button btnRegistarDocente1;

    @FXML
    private Button btnListarDocente;

    @FXML
    private Button listarDocentebtn;

    @FXML
    private JFXButton btnRegistarDocentes;

    @FXML
    private JFXButton btnRegistarEstudante;

    @FXML
    private JFXButton btnRegistarTurmas;

    @FXML
    private JFXButton btnRegistarUtilizador;

    @FXML
    private JFXButton btnTurmas;

    @FXML
    private Button btnUtilizadores;

    @FXML
    private TableColumn<?, ?> colUserEmail, colUserCreatedAt;

    @FXML
    private TableColumn<?, ?> colUserCreatedAt1;

    @FXML
    private TableColumn<?, ?> colUserCreatedAt11;

    @FXML
    private TableColumn<?, ?> colUserEmail1;

    @FXML
    private TableColumn<?, ?> colUserEmail11;

    @FXML
    private TableColumn<?, ?> colUserId, colUserName, emailColumn;

    @FXML
    private TableColumn<?, ?> colUserId1;

    @FXML
    private TableColumn<?, ?> colUserId11;

    @FXML
    private TableColumn<?, ?> colUserName1;

    @FXML
    private TableColumn<?, ?> colUserName11;

    @FXML
    private TableView<Estudante> estudanteTable;

    @FXML
    private TableView<User> tableViewUsers;

    @FXML
    private ImageView iconVoltarPanelDocentes;

    @FXML
    private ImageView iconVoltarPanelEstudantes;

    @FXML
    private ImageView iconVoltarPanelEstudantes1;

    @FXML
    private ImageView iconVoltarPanelUtilizadores;

    @FXML
    private Label lblCursosDashboard;

    @FXML
    private Label lblDisciplinasDashboard;

    @FXML
    private Label lblDisciplinasDashboard1;

    @FXML
    private Label lblDocenteDashboard;

    @FXML
    private Label lblEstudanteDashboard;

    @FXML
    private Label lblHome;

    @FXML
    private Label lblUtilizadorDashboard;

    @FXML
    private ImageView imgBackDocente;

    @FXML
    private TableColumn<?, ?> idEstudanteColumn;

    @FXML
    private TableColumn<?, ?> nomeColumn;

    @FXML
    private TableColumn<Estudante, LocalDateTime> createdAtEstudanteColumn;

    @FXML
    private Pane panelAddDocentes;

    @FXML
    private Pane panelAddUtilizadores;

    @FXML
    private Pane panelHomeCursos;

    @FXML
    private Pane panelHomeDisciplinas;

    @FXML
    private Pane panelHomeDocentes;

    @FXML
    private Pane panelHomeEstudante;

    @FXML
    private Pane panelHomeEstudante11;

    @FXML
    private Pane panelHomeHome;

    @FXML
    private Pane panelHomeTurmas;

    @FXML
    private Pane panelHomeUtilizadores;

    @FXML
    private Pane panelListarDocentes;

    @FXML
    private Pane panelListarEstudantes;

    @FXML
    private Tab tabAddCurso, tabAddDisciplina, tabAddTurma, tabAdicionarDocentes, tabAdicionarEstudantes;

    @FXML
    private Tab tabAdicionarUtilizadores;

    @FXML
    private Tab tabCursos;

    @FXML
    private Tab tabDisciplinas;

    @FXML
    private Tab tabDocentes;

    @FXML
    private Tab tabEstudantes;

    @FXML
    private Tab tabHome;

    @FXML
    private Tab tabListarCursos;

    @FXML
    private Tab tabListarDisciplinas;

    @FXML
    private Tab tabListarDocentes;

    @FXML
    private Tab tabListarEstudantes;

    @FXML
    private Tab tabListarUlitizadores;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabTurmas;

    @FXML
    private Tab tabUtilizadores;

    @FXML
    private TableView<?> tableViewCursos;

    @FXML
    private TableView<?> tableViewDisciplinas;

    @FXML
    private TableColumn<?, ?> telefoneColumn;

    @FXML
    private TextField txtPesquisarDocentes;

    @FXML
    private TextField txtPesquisarEstudante;

    private EstudanteDAOImpl estudanteDAO;
    private UserDAOImpl userDAO;

    @FXML
    void initialize() {

        initPanels();

        // Use a implementação concreta do DAO
        this.estudanteDAO = new EstudanteDAOImpl(HibernateUtil.getSessionFactory());
        this.userDAO = new UserDAOImpl(HibernateUtil.getSessionFactory());


        // Configure as colunas da tabela
        idEstudanteColumn.setCellValueFactory(new PropertyValueFactory<>("codigoestudante"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        telefoneColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        createdAtEstudanteColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        colUserId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUserEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUserCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));


        // Carregar os estudantes
        listarEstudantes();
        ListarUsers();

    }

    @FXML
    public void initPanels(){

        // TODO: Estudantes
        btnEstudantes.setOnAction(event -> tabPane.getSelectionModel().select(tabEstudantes));
        btnRegistarEstudante.setOnAction(event -> tabPane.getSelectionModel().select(tabAdicionarEstudantes));
        btnListarEstudantes.setOnAction(event -> tabPane.getSelectionModel().select(tabListarEstudantes));
        lblEstudanteDashboard.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabHome));
        btnAddEstudante.setOnAction(event -> tabPane.getSelectionModel().select(tabAdicionarEstudantes));
        iconVoltarPanelEstudantes.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabEstudantes));
        iconVoltarPanelEstudantes1.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabEstudantes));

        // TODO: Docentes
        btnDocentes.setOnAction(event -> tabPane.getSelectionModel().select(tabDocentes));
        btnRegistarDocentes.setOnAction(event -> tabPane.getSelectionModel().select(tabAdicionarDocentes));
        btnListarDocentes.setOnAction(event -> tabPane.getSelectionModel().select(tabListarDocentes));
        lblDocenteDashboard.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabHome));
        btnAddDocente.setOnAction(event -> tabPane.getSelectionModel().select(tabAdicionarDocentes));
        imgBackDocente.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabDocentes));
        btnListarDocente.setOnAction(event -> tabPane.getSelectionModel().select(tabListarDocentes));

        // TODO: Cursos
        btnCursos.setOnAction(event -> tabPane.getSelectionModel().select(tabCursos));
        btnListarCursos.setOnAction(event -> tabPane.getSelectionModel().select(tabCursos));
        btnRegistarCurso.setOnAction(event -> tabPane.getSelectionModel().select(tabAddCurso));

        // TODO: Turmas
        btnTurmas.setOnAction(event -> tabPane.getSelectionModel().select(tabTurmas));
        btnListarTurmas.setOnAction(event -> tabPane.getSelectionModel().select(tabTurmas));
        btnRegistarTurmas.setOnAction(event -> tabPane.getSelectionModel().select(tabAddTurma));

        // TODO: Disciplinas
        btnDisciplinas.setOnAction(event -> tabPane.getSelectionModel().select(tabDisciplinas));
        btnListarDisciplinas.setOnAction(event -> tabPane.getSelectionModel().select(tabListarDisciplinas));
        btnRegistarDisciplina.setOnAction(event -> tabPane.getSelectionModel().select(tabAddDisciplina));

        // TODO: Utilizadores
        btnUtilizadores.setOnAction(event -> tabPane.getSelectionModel().select(tabUtilizadores));
        btnListarUtilizadores.setOnAction(event -> tabPane.getSelectionModel().select(tabListarUlitizadores));
        btnRegistarUtilizador.setOnAction(event -> tabPane.getSelectionModel().select(tabAdicionarUtilizadores));
        listarDocentebtn.setOnAction(event -> tabPane.getSelectionModel().select(tabListarUlitizadores));

    }

    @FXML
    public void listarEstudantes() {
        List<Estudante> estudantes = estudanteDAO.readAll();
        System.out.println("Número de estudantes encontrados: " + (estudantes != null ? estudantes.size() : 0));
        actualizarTabelaEstudantes(estudantes);
    }

    @FXML
    private void ListarUsers() {
        List<User> users = userDAO.readAll();
        ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
        tableViewUsers.setItems(observableUsers);
        actualizarTabelaUsers(users);
    }

    private void actualizarTabelaEstudantes(List<Estudante> estudantes) {
        if (estudantes != null && !estudantes.isEmpty()) {
            ObservableList<Estudante> observableEstudantes = FXCollections.observableArrayList(estudantes);
            estudanteTable.setItems(observableEstudantes);
        } else {
            estudanteTable.getItems().clear();
            System.out.println("Nenhum estudante encontrado ou lista de estudantes está vazia.");
        }
    }

    private void actualizarTabelaUsers(List<User> users) {
        if (users != null && !users.isEmpty()) {
            ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
            tableViewUsers.setItems(observableUsers);
        } else {
            tableViewUsers.getItems().clear();
            System.out.println("Nenhum utilizador encontrado ou lista de utilizadores está vazia.");
        }
    }


}

package com.uem.sgnfx.Controllers.Admin;

/**
 * Created by USER on 19/09/2024.
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.uem.sgnfx.DAO.*;
import com.uem.sgnfx.Models.*;
import com.uem.sgnfx.Services.EmailService;
import com.uem.sgnfx.Services.SessionManager;
import com.uem.sgnfx.Utils.HibernateUtil;

import com.uem.sgnfx.Validations.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.CheckListView;
import org.controlsfx.control.SearchableComboBox;
import org.mindrot.jbcrypt.BCrypt;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminController {

    // Botões
    @FXML
    private Button btnAddDocente, btnAddEstudante, btnBuscaDocente, btnBuscarEstudantes, btnCursos, btnDocentes;
    @FXML
    private Button btnTurmas, btnDisciplinas, btnEstudantes, btnDepartamentos, btnRegistarDocente, btnRegistarDocente1, btnListarDocente, listarDocentebtn, btnCarregarEstudantes, btnUtilizadores;
    @FXML
    private JFXButton btnRegistarDepartemento, btnListarCursos, btnListarDisciplinas, btnListarDocentes, btnListarEstudantes, btnListarTurmas, btnListarUtilizadores;
    @FXML
    private JFXButton btnListarDepartementos, btnRegistarCurso, btnRegistarDisciplina, btnRegistarDocentes, btnRegistarEstudante, btnRegistarTurmas, btnRegistarUtilizador, btnRegistarDepartamento;

    // Colunas das Tabelas
    @FXML
    private TableColumn<?, ?> colUserEmail, colUserId, colUserName, emailColumn;
    @FXML
    private TableColumn<?, ?> colDocenteId, colDocenteName, colDocenteEmail, colDocenteCreatedAt, colDocenteUpdatedAt, colDocenteIsActive;
    @FXML
    private TableColumn<?, ?> colCursoId, colCursoName, colCursoDepartamento, colCursoCreatedAt;
    @FXML
    private TableColumn<?, ?> colDisciplinaId, colDisciplinaName, colDisciplinaCurso, colDisciplinaCreatedAt;
    @FXML
    private TableColumn<?, ?> colTurmaId, colTurmaName, colTurmaCurso, colTurmaCreatedAt;
    @FXML
    private TableColumn<?, ?> colDepartamentoCreatedAt, colDepartamentoSigla, colDepartamentoId, colCursoUpdatedAt, colDepartementoName;
    @FXML
    private TableColumn<Estudante, Instant> createdAtEstudanteColumn;

    @FXML
    private TableColumn<User, Instant> colUserCreatedAt, colUserUpdatedAt;

    @FXML
    private TableColumn<?, ?> idEstudanteColumn, nomeColumn, telefoneColumn;

    // Tabelas
    @FXML
    private TableView<Estudante> estudanteTable;
    @FXML
    private TableView<User> tableViewUsers;
    @FXML
    private TableView<Docente> docenteTableView;
    @FXML
    private TableView<Disciplina> disciplinaTableView;
    @FXML
    private TableView<Curso> cursoTableView;
    @FXML
    private TableView<Turma> turmaTableView;
    @FXML
    private TableView<Departamento> departementoTableView;
    @FXML
    private TableView<?> tableViewCursos, tableViewDisciplinas;
    @FXML
    private CheckListView checkListCursoRegime;

    // Imagens
    @FXML
    private ImageView iconVoltarPanelDocentes, iconVoltarPanelEstudantes, iconVoltarPanelEstudantes1, iconVoltarPanelUtilizadores, imgBackDocente;

    // Labels
    @FXML
    private Label lblCountEstudantes, lblLoggedUserName, lblCursosDashboard, lblDisciplinasDashboard, lblDisciplinasDashboard1, lblDocenteDashboard, lblEstudanteDashboard, lblHome, lblUtilizadorDashboard;

    // Panes
    @FXML
    private Pane panelAddDocentes, panelAddUtilizadores, panelHomeCursos, panelHomeDisciplinas, panelHomeDocentes, panelHomeEstudante, panelHomeEstudante11;
    @FXML
    private Pane panelHomeHome, panelHomeTurmas, panelHomeUtilizadores, panelListarDocentes, panelListarEstudantes;

    // Abas (Tabs)
    @FXML
    private Tab tabAddCurso, tabAddDisciplina, tabAddTurma, tabAdicionarDocentes, tabAdicionarEstudantes, tabAdicionarUtilizadores, tabAddDepartamento;
    @FXML
    private Tab tabCursos, tabDisciplinas, tabDocentes, tabEstudantes, tabHome, tabListarCursos, tabListarDisciplinas, tabListarDocentes, tabListarEstudantes, tabListarUlitizadores, tabListarTurmas, tabListarDepartamentos, tabTurmas, tabUtilizadores, tabTabDepartemento, tabDepartemento;
    @FXML
    private TabPane tabPane;

    // Campos de Texto e Pesquisa
    @FXML
    private TextField txtPesquisarDocentes, txtPesquisarEstudante;

    @FXML
    private SearchableComboBox<Curso> txtLiveSearchEstudante;

    @FXML
    private JFXComboBox<Departamento> cbDepartamentoCurso;

    @FXML
    private JFXComboBox<Curso> cbCursoDisciplina;

    @FXML
    private ComboBox<Curso> cbEstudanteCurso;

    @FXML ComboBox<Semestre> cbDisciplinaSemestre;

    @FXML
    private ComboBox<String> cbUserGenero;

    @FXML
    private TextField txtNomeDepartamento, txtNomeCurso, txtSiglaDepartamento, txtNomeDisciplina, txtUserName, txtUserApelido, txtUserEmail, txtUserNomeUtilizador, txtUserPassword;

    @FXML
    JFXCheckBox txtUserActive, txtUserAdmin;

    @FXML
    private TextArea txtDescricaoDepartamento, txtDescricaoCurso, txtDescricaoDisciplina;

    private AlertMessage alertMessage;

    // CRUD de Estudantes
    @FXML
    private TextField txtEstudanteCodigo, txtEstudanteNome, txtEstudanteApelido, txtEstudanteEmail, txtEstudanteDocumento, txtEstudanteNuit, txtEstudanteTelefone, txtEstudanteEndereco, txtEstudanteSenha, txtEstudanteNacionalidade, txtEstudanteNaturalidade;
    @FXML
    private ComboBox<String> comboEstudanteProvincia, cbEstudanteGenero, cbEstudanteDocumento, cbEstudanteNaturalidade, cbEstudanteNacionalidade;
    @FXML
    private DatePicker dateEstudanteNascimento;
    @FXML
    private TableView<Estudante> estudanteTableView;

    @FXML
    private BarChart<String, Number> chartEstudantes;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private Map<String, Regime> regimeMap = new HashMap<String, Regime>();
    private Map<String, CursoRegime> regimeCurso = new HashMap<String, CursoRegime>();

    private MetodosGenericos metodosGenericos;
    private UserDAOImpl userDAO;
    private DocenteDAOImpl docenteDAO;
    private EstudanteDAOImpl estudanteDAO;
    private DisciplinaDAOImpl disciplinaDAO;
    private CursoDAOImpl cursoDAO;
    private TurmaDAOImpl turmaDAO;
    private DepartamentoDAOImpl departamentoDAO;
    private SemestreDAOImpl semestreDAO;
    private RegimeDAOImpl regimeDAO;
    private CursoRegimeDAOImpl cursoRegimeDAO;

    @FXML
    void initialize() {
        setupEstudantesActions();
        setupDocentesActions();
        setupCursosActions();
        setupTurmasActions();
        setupDisciplinasActions();
        setupUtilizadoresActions();
        setupDepartamentosActions();

        this.alertMessage = new AlertMessage();

        User loggedInUser = SessionManager.getLoggedInEntity();

        if (loggedInUser != null) {
            lblLoggedUserName.setText("Bem-vindo, " + loggedInUser.getName());
        }else {
            lblLoggedUserName.textProperty().setValue("Bem-vindo, null");
        }

        // Use a implementação concreta do DAO
        this.metodosGenericos = new MetodosGenericos(HibernateUtil.getSessionFactory());
        this.userDAO = new UserDAOImpl(HibernateUtil.getSessionFactory());
        this.docenteDAO = new DocenteDAOImpl(HibernateUtil.getSessionFactory());
        this.estudanteDAO = new EstudanteDAOImpl(HibernateUtil.getSessionFactory());
        this.disciplinaDAO = new DisciplinaDAOImpl(HibernateUtil.getSessionFactory());
        this.cursoDAO = new CursoDAOImpl(HibernateUtil.getSessionFactory());
        this.turmaDAO = new TurmaDAOImpl(HibernateUtil.getSessionFactory());
        this.departamentoDAO = new DepartamentoDAOImpl(HibernateUtil.getSessionFactory());
        this.semestreDAO = new SemestreDAOImpl(HibernateUtil.getSessionFactory());
        this.regimeDAO = new RegimeDAOImpl(HibernateUtil.getSessionFactory());
        this.cursoRegimeDAO = new CursoRegimeDAOImpl(HibernateUtil.getSessionFactory());

        // Configure as colunas da tabela
        colUserId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUserEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUserCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        colUserCreatedAt.setCellFactory(column -> new TableCell<User, Instant>() {
            @Override
            protected void updateItem(Instant item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    LocalDateTime localDateTime = LocalDateTime.ofInstant(item, ZoneId.systemDefault());
                    setText(localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                }
            }
        });

        idEstudanteColumn.setCellValueFactory(new PropertyValueFactory<>("codigoEstudante"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        telefoneColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        createdAtEstudanteColumn.setCellFactory(column -> new TableCell<Estudante, Instant>() {
            @Override
            protected void updateItem(Instant item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    LocalDateTime localDateTime = LocalDateTime.ofInstant(item, ZoneId.systemDefault());
                    setText(localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                }
            }
        });


        colDocenteId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDocenteName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colDocenteEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDocenteCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        colDocenteUpdatedAt.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));
        colDocenteIsActive.setCellValueFactory(new PropertyValueFactory<>("isActive"));

        colDisciplinaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDisciplinaName.setCellValueFactory(new PropertyValueFactory<>("designacao"));
        colDisciplinaCurso.setCellValueFactory(new PropertyValueFactory<>("CursoNome"));
        colDisciplinaCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        colCursoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCursoName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCursoDepartamento.setCellValueFactory(new PropertyValueFactory<>("DepartamentoNome"));
        colCursoCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        colCursoUpdatedAt.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));

        colTurmaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTurmaName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colTurmaCurso.setCellValueFactory(new PropertyValueFactory<>("CursoNome"));
        colTurmaCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        colDepartamentoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDepartementoName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colDepartamentoSigla.setCellValueFactory(new PropertyValueFactory<>("sigla"));
        colDepartamentoCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        // Adicionando valores válidos para os ComboBoxes
        cbEstudanteDocumento.getItems().addAll("BI", "Passaporte", "Carta de Condução");
        cbEstudanteGenero.getItems().addAll("Masculino", "Feminino", "Outro");
//        cbEstudanteNacionalidade.getItems().addAll("Mocambican");

        // Seleciona a primeira opção por padrão
        cbEstudanteDocumento.getSelectionModel().selectFirst();
        cbEstudanteGenero.getSelectionModel().selectFirst();
        cbEstudanteGenero.getSelectionModel().selectFirst();

        // Carregar os estudantes
        ListarUsers();
        ListarDocentes();
        listarEstudantes();
        listarDisciplinas();
        listarCursos();
        listarTurmas();
        listarDepartamentos();
        buscarEstudantesPorCurso();
        listarSemestres();
        configurarEixos();
        carregarDadosEstudantesPorCurso();
    }

    @FXML
    private void ListarUsers() {
        List<User> users = userDAO.readAll();
        ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
        tableViewUsers.setItems(observableUsers);
        actualizarTabelaUsers(users);
    }

    @FXML
    private void ListarDocentes() {
        List<Docente> docentes = docenteDAO.readAll();
        ObservableList<Docente> observableDocentes = FXCollections.observableArrayList(docentes);
        docenteTableView.setItems(observableDocentes);
        actualizarTabelaDocentes(docentes);
    }

    @FXML
    public void listarEstudantes(){
        List<Estudante> estudantes = estudanteDAO.readAll();
        System.out.println("Número de estudantes encontrados: " + (estudantes != null ? estudantes.size() : 0));
        actualizarTabelaEstudantes(estudantes);
        inicializarComboBoxCurso(cbEstudanteCurso);
    }

    public void listarDisciplinas(){
        List<Disciplina> disciplinas = disciplinaDAO.readAll();
        for (Disciplina disciplina : disciplinas) {
            disciplina.getCurso().getNome();
        }
        actualizarDisciplinas(disciplinas);
        inicializarComboBoxCurso(cbCursoDisciplina);
    }

    public void listarCursos(){

        checkListCursoRegime.getItems().clear();
        List<Curso> cursos = cursoDAO.readAll();
        List<Regime> regimes = regimeDAO.readAll();

        for (Regime regime : regimes) {
            String displayRegimes = regime.getNome();
            checkListCursoRegime.getItems().add(displayRegimes);
            regimeMap.put(displayRegimes, regime);
        }

        actualizarCursos(cursos);
    }

    public void listarTurmas(){
        List<Turma> turmas = turmaDAO.readAll();
        for (Turma turma : turmas) {
            turma.getCurso().getNome();
        }
        actualizarTurmasList(turmas);
    }

    public void listarDepartamentos(){
        List<Departamento> departamentos = departamentoDAO.readAll();
        actualizarDepartamentos(departamentos);

        ObservableList<Departamento> observableCursos = FXCollections.observableArrayList(departamentos);
        cbDepartamentoCurso.setItems(observableCursos);
        cbDepartamentoCurso.setCellFactory(lv -> new ListCell<Departamento>() {
            @Override
            protected void updateItem(Departamento departamento, boolean empty) {
                super.updateItem(departamento, empty);
                if (empty || departamento == null) {
                    setText(null);
                } else {
                    setText(departamento.getNome());
                }
            }
        });

        cbDepartamentoCurso.setButtonCell(new ListCell<Departamento>() {
            @Override
            protected void updateItem(Departamento departamento, boolean empty) {
                super.updateItem(departamento, empty);
                if (empty || departamento == null) {
                    setText(null);
                } else {
                    setText(departamento.getNome());  // Exibe o nome do curso na seleção
                }
            }
        });
    }

    private void listarSemestres(){
        List<Semestre> semestreList = semestreDAO.readAll();
        ObservableList<Semestre> observableCursos = FXCollections.observableArrayList(semestreList);
        cbDisciplinaSemestre.setItems(observableCursos);
        semestreDAO.inicializarComboBoxSemestre(cbDisciplinaSemestre);
    }

    @FXML
    public void buscarEstudantesPorCriterio() {
        String criterio = txtPesquisarEstudante.getText();

        if (criterio == null || criterio.isEmpty()) {
            alertMessage.showAlertWarning("Por favor, insira um critério de pesquisa.");
            return;
        }

        //List<Estudante> estudantes = estudanteDAO.buscarPorCriterioUnico(criterio);
        List<Estudante> estudantes = metodosGenericos.buscarPorCriterio(Estudante.class,"nome" , criterio);

        actualizarTabelaEstudantes(estudantes);
    }

    @FXML
    public void buscarEstudantesPorCurso(){
        // Carregar opções de cursos no SearchableComboBox
        List<Curso> cursos = cursoDAO.readAll();
        ObservableList<Curso> observableCursos = FXCollections.observableArrayList(cursos);
        txtLiveSearchEstudante.setItems(observableCursos);

        // Configurar a CellFactory para exibir o nome dos cursos
        txtLiveSearchEstudante.setCellFactory(lv -> new ListCell<Curso>() {
            @Override
            protected void updateItem(Curso curso, boolean empty) {
                super.updateItem(curso, empty);
                if (empty || curso == null) {
                    setText(null);
                } else {
                    setText(curso.getNome());  // Exibe o nome do curso
                }
            }
        });



        // Também exibe o nome do curso quando um item é selecionado
        txtLiveSearchEstudante.setButtonCell(new ListCell<Curso>() {
            @Override
            protected void updateItem(Curso curso, boolean empty) {
                super.updateItem(curso, empty);
                if (empty || curso == null) {
                    setText(null);
                } else {
                    setText(curso.getNome());  // Exibe o nome do curso na seleção
                }
            }
        });

        Curso cursoSelecionado = txtLiveSearchEstudante.getValue();
        if (cursoSelecionado != null) {
            List<Estudante> estudantes = estudanteDAO.buscarPorCriterioCurso(cursoSelecionado.getId());
            actualizarTabelaEstudantes(estudantes);
        } else {
            System.out.println("Nenhum curso selecionado");
        }
    }

    public void inicializarComboBoxCurso(ComboBox<Curso> comboBoxCurso) {
        // Obtém a lista de cursos do DAO
        List<Curso> cursos = cursoDAO.readAll();
        ObservableList<Curso> observableCursos = FXCollections.observableArrayList(cursos);
        comboBoxCurso.setItems(observableCursos);

        // Define a fábrica de células para exibir o nome do curso na lista
        comboBoxCurso.setCellFactory(lv -> new ListCell<Curso>() {
            @Override
            protected void updateItem(Curso curso, boolean empty) {
                super.updateItem(curso, empty);
                if (empty || curso == null) {
                    setText(null);
                } else {
                    setText(curso.getNome());
                }
            }
        });

        // Define a célula do botão para exibir o nome do curso selecionado
        comboBoxCurso.setButtonCell(new ListCell<Curso>() {
            @Override
            protected void updateItem(Curso curso, boolean empty) {
                super.updateItem(curso, empty);
                if (empty || curso == null) {
                    setText(null);
                } else {
                    setText(curso.getNome());
                }
            }
        });
    }

    private void actualizarTabelaUsers(List<User> users) {
        if (users != null && !users.isEmpty()) {
            ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
            tableViewUsers.setItems(observableUsers);
        } else {
            tableViewUsers.getItems().clear();
            //System.out.println("Nenhum utilizador encontrado ou lista de utilizadores está vazia.");
        }
    }

    private void actualizarTabelaDocentes(List<Docente> docentes) {
        if (docentes != null && !docentes.isEmpty()) {
            ObservableList<Docente> observableDocentes = FXCollections.observableArrayList(docentes);
            docenteTableView.setItems(observableDocentes);
        }else {
            docenteTableView.getItems().clear();
        }
    }

    private void actualizarTabelaEstudantes(List<Estudante> estudantes) {
        if (estudantes != null && !estudantes.isEmpty()) {
            ObservableList<Estudante> observableEstudantes = FXCollections.observableArrayList(estudantes);
            estudanteTable.setItems(observableEstudantes);
        } else {
            estudanteTable.getItems().clear();
            //System.out.println("Nenhum estudante encontrado ou lista de estudantes está vazia.");
        }
    }

    public void actualizarDisciplinas(List<Disciplina> disciplinas){
        if (disciplinas != null && !disciplinas.isEmpty()) {
            ObservableList<Disciplina> observableDisciplinas = FXCollections.observableArrayList(disciplinas);
            disciplinaTableView.setItems(observableDisciplinas);
        }else {
            disciplinaTableView.getItems().clear();
        }
    }

    public void actualizarCursos(List<Curso> cursos){
        if (cursos != null && !cursos.isEmpty()) {
            ObservableList<Curso> observableCursos = FXCollections.observableArrayList(cursos);
            cursoTableView.setItems(observableCursos);
        }else {
            cursoTableView.getItems().clear();
        }
    }

    public void actualizarTurmasList(List<Turma> turmaList){
        if (turmaList != null && !turmaList.isEmpty()) {
            ObservableList<Turma> observableTurmas = FXCollections.observableArrayList(turmaList);
            turmaTableView.setItems(observableTurmas);
        }else {
            turmaTableView.getItems().clear();
        }
    }

    public void actualizarDepartamentos(List<Departamento> departamentos){
        if (departamentos != null && !departamentos.isEmpty()) {
            ObservableList<Departamento> observableDepartamentos = FXCollections.observableArrayList(departamentos);
            departementoTableView.setItems(observableDepartamentos);
        }else {
            departementoTableView.getItems().clear();
        }
    }

    @FXML
    public void adicionarUtilizadores(){

        cbEstudanteGenero.getItems().addAll("Selecione uma opção", "Masculino", "Feminino");
        cbUserGenero.getSelectionModel().selectFirst();

        String nome = txtUserName.getText();
        String apelido = txtUserApelido.getText();
        String username = txtUserNomeUtilizador.getText();
        String email = txtUserEmail.getText();
        String genero = "Masculino";
        Boolean isActive = txtUserActive.isSelected();
        Boolean isAdmin = txtUserAdmin.isSelected();
        String password = BCrypt.hashpw(txtUserPassword.getText(), BCrypt.gensalt());

        if (nome != null && !nome.isEmpty()) {
            User user = new User(nome, apelido, username, email, genero, isActive, isAdmin, password, Instant.now(), Instant.now(), null);
            userDAO.create(user);

            // Envia o endereço eletrónico após criar o usuário
//            EmailService emailService = new EmailService();
//            emailService.enviarEmailNovoUsuario(email, nome, apelido, username, email);

            alertMessage.showAlertSuccess("Utilizador " + nome + " criado com sucesso!");
        } else {
            alertMessage.showAlertInfo("Por favor, preencha todos os campos!");
        }

    }

    @FXML
    private void adicionarEstudante() {
        String nome = txtEstudanteNome.getText();
        String apelido = txtEstudanteApelido.getText();
        String email = txtEstudanteEmail.getText();
        String telefone = txtEstudanteTelefone.getText();
        String endereco = txtEstudanteEndereco.getText();
        String tipoDocumento = cbEstudanteDocumento.getValue();
        String genero = cbEstudanteGenero.getValue();
        LocalDate dataNascimento = dateEstudanteNascimento.getValue();
        String estadoCivil = "Solteiro";
        String nacionalidade = txtEstudanteNacionalidade.getText();
        String naturalidade = txtEstudanteNaturalidade.getText();
        User loggedInUser = SessionManager.getLoggedInEntity();
        Curso curso = cbEstudanteCurso.getValue();
        Boolean isActive = true;
        Boolean isAdmin = false;

        try {
            // Verificação de idade mínima de 18 anos
            if (dataNascimento != null) {
                LocalDate today = LocalDate.now();
                Period age = Period.between(dataNascimento, today);
                if (age.getYears() < 18) {
                    alertMessage.showAlertWarning("O estudante deve ter no mínimo 18 anos para ser cadastrado.");
                    return;
                }
            } else {
                alertMessage.showAlertWarning("Por favor, selecione uma data de nascimento válida.");
                return;
            }

            // Gerar o código do estudante
            Long ultimoId = estudanteDAO.obterUltimoId(); // Método para buscar o último ID da tabela Estudante
            int anoAtual = LocalDate.now().getYear();
            String codigo = (ultimoId + 1) + "" + anoAtual;

            // Gerar senha do estudante
            String senha = BCrypt.hashpw(codigo, BCrypt.gensalt());

            // Verificação de campos obrigatórios
            if (!nome.isEmpty() && !email.isEmpty() && curso != null) {
                Estudante estudante = new Estudante(
                        nome, apelido, email, telefone, codigo, endereco, tipoDocumento, genero,
                        dataNascimento, estadoCivil, nacionalidade, naturalidade, loggedInUser,
                        curso, isActive, isAdmin, senha, Instant.now(), Instant.now()
                );
                estudanteDAO.create(estudante);
                alertMessage.showAlertSuccess("Estudante criado com sucesso!");
            } else {
                alertMessage.showAlertWarning("Por favor, preencha todos os campos obrigatórios!");
            }
        } catch (Exception exception) {
            System.out.println("Erro: " + exception.getMessage());
        }
    }

    @FXML
    private void adicionarDocente(){
        
    }

    @FXML
    public void adicionarDepartamento() {
        String nome = txtNomeDepartamento.getText();
        String sigla = txtSiglaDepartamento.getText();
        String descricao = txtDescricaoDepartamento.getText();

        if (nome.isEmpty() || sigla.isEmpty()) {
            alertMessage.showAlertInfo("Por favor, preecha todos os campos!");
            return;
        }else {
            departamentoDAO.createDepartamento(nome, sigla, descricao);
        }

        alertMessage.showAlertSuccess("Departamento criado com sucesso!");

        listarDepartamentos();
        limparCamposDepartamento();
    }

    @FXML
    public void adicionarCurso() {
        String nome = txtNomeCurso.getText();
        String descricao = txtDescricaoCurso.getText();
        Departamento departamento = cbDepartamentoCurso.getValue();

        if (nome.isEmpty()){
            alertMessage.showAlertWarning("Por favor, preecha todos os campos!");
            return;
        }else {

            Curso curso = new Curso(nome, descricao, departamento, Instant.now(), Instant.now());

            Curso cursoCriado = cursoDAO.create(curso);

            List<String> regimesSelecionadas = checkListCursoRegime.getCheckModel().getCheckedItems();
            if (regimesSelecionadas.isEmpty()){
                alertMessage.showAlertWarning("Por favor, selecione pelo menos um regime!.");
                return;
            }

            for (String regimeaString : regimesSelecionadas) {
                Regime regime = regimeMap.get(regimeaString);
                //CursoRegime cursoRegime = regimeCurso.get(regimeaString);

                if (regime != null) {
                    CursoRegime cursoRegimes = new CursoRegime();
                    cursoRegimes.setCurso(cursoCriado);
                    cursoRegimes.setRegime(regime);
                    cursoRegimes.setCreatedAt(Instant.now());
                    cursoRegimes.setUpdatedAt(Instant.now());
                    //cursoRegime.setCurso(curso);
                    cursoRegimeDAO.create(cursoRegimes);
                }
            }

        }

        alertMessage.showAlertSuccess("Curso criado com sucesso!");

        listarCursos();
        limparCamposCurso();
    }

    public void adicionarDisciplina(){
        String nome = txtNomeDisciplina.getText();
        String descricao = txtDescricaoDisciplina.getText();
        Semestre semestre = cbDisciplinaSemestre.getValue();
        Curso curso = cbCursoDisciplina.getValue();

        if (nome.isEmpty() || curso == null) {
            alertMessage.showAlertWarning("Por favor, preecha todos os campos!");
            return;
        }else {
            disciplinaDAO.createDisciplina(nome, curso, semestre);
        }

        alertMessage.showAlertSuccess("Disciplina criado com sucesso!");

        listarDisciplinas();
        limparCamposDisciplina();

    }

    private void configurarEixos() {
        xAxis.setLabel("Cursos");
        yAxis.setLabel("Número de Estudantes");
    }

    private void carregarDadosEstudantesPorCurso() {
        // Obter contagem de estudantes por curso
        Map<String, Long> contagemEstudantesPorCurso = estudanteDAO.obterContagemEstudantesPorCurso();

        // Criar a série de dados
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Estudantes por Curso");

        // Adicionar dados à série
        for (Map.Entry<String, Long> entry : contagemEstudantesPorCurso.entrySet()) {
            String cursoNome = entry.getKey();
            Long quantidadeEstudantes = entry.getValue();
            series.getData().add(new XYChart.Data<>(cursoNome, quantidadeEstudantes));
        }

        // Adicionar a série ao gráfico
        chartEstudantes.getData().clear(); // Limpa dados anteriores, se houver
        chartEstudantes.getData().add(series);
        long totalEstudantes = estudanteDAO.count();
        lblCountEstudantes.setText(String.valueOf(totalEstudantes));
    }


    // TODO: Método para limpar os campos de texto
    private void limparCamposDepartamento() {
        txtNomeDepartamento.clear();
        txtSiglaDepartamento.clear();
        txtDescricaoDepartamento.clear();
    }

    private void limparCamposCurso() {
        txtNomeCurso.clear();
        txtDescricaoCurso.clear();
        cbDepartamentoCurso.setValue(null);
    }

    private void limparCamposDisciplina(){
        txtNomeDisciplina.clear();
        txtDescricaoDisciplina.clear();
        cbCursoDisciplina.setValue(null);
    }
























    // TODO: Estudantes
    private void setupEstudantesActions() {
        btnEstudantes.setOnAction(event -> tabPane.getSelectionModel().select(tabEstudantes));
        btnRegistarEstudante.setOnAction(event -> tabPane.getSelectionModel().select(tabAdicionarEstudantes));
        btnListarEstudantes.setOnAction(event -> tabPane.getSelectionModel().select(tabListarEstudantes));
        lblEstudanteDashboard.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabHome));
        btnAddEstudante.setOnAction(event -> tabPane.getSelectionModel().select(tabAdicionarEstudantes));
        iconVoltarPanelEstudantes.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabEstudantes));
        iconVoltarPanelEstudantes1.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabEstudantes));
    }

    // TODO: Docentes
    private void setupDocentesActions() {
        btnDocentes.setOnAction(event -> tabPane.getSelectionModel().select(tabDocentes));
        btnRegistarDocentes.setOnAction(event -> tabPane.getSelectionModel().select(tabAdicionarDocentes));
        btnListarDocentes.setOnAction(event -> tabPane.getSelectionModel().select(tabListarDocentes));
        lblDocenteDashboard.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabHome));
        btnAddDocente.setOnAction(event -> tabPane.getSelectionModel().select(tabAdicionarDocentes));
        iconVoltarPanelDocentes.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabDocentes));
        imgBackDocente.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabDocentes));
        btnListarDocente.setOnAction(event -> tabPane.getSelectionModel().select(tabListarDocentes));
    }

    // TODO: Cursos
    private void setupCursosActions() {
        btnCursos.setOnAction(event -> tabPane.getSelectionModel().select(tabCursos));
        btnListarCursos.setOnAction(event -> tabPane.getSelectionModel().select(tabListarCursos));
        btnRegistarCurso.setOnAction(event -> tabPane.getSelectionModel().select(tabAddCurso));
    }

    // TODO: Turmas
    private void setupTurmasActions() {
        btnTurmas.setOnAction(event -> tabPane.getSelectionModel().select(tabTurmas));
        btnListarTurmas.setOnAction(event -> tabPane.getSelectionModel().select(tabListarTurmas));
        btnRegistarTurmas.setOnAction(event -> tabPane.getSelectionModel().select(tabAddTurma));
    }

    // TODO: Disciplinas
    private void setupDisciplinasActions() {
        btnDisciplinas.setOnAction(event -> tabPane.getSelectionModel().select(tabDisciplinas));
        btnListarDisciplinas.setOnAction(event -> tabPane.getSelectionModel().select(tabListarDisciplinas));
        btnRegistarDisciplina.setOnAction(event -> tabPane.getSelectionModel().select(tabAddDisciplina));
    }

    // TODO: Utilizadores
    private void setupUtilizadoresActions() {
        btnUtilizadores.setOnAction(event -> tabPane.getSelectionModel().select(tabUtilizadores));
        btnListarUtilizadores.setOnAction(event -> tabPane.getSelectionModel().select(tabListarUlitizadores));
        btnRegistarUtilizador.setOnAction(event -> tabPane.getSelectionModel().select(tabAdicionarUtilizadores));
        //listarDocentebtn.setOnAction(event -> tabPane.getSelectionModel().select(tabListarUlitizadores));
    }

    // TODO: Departamentos
    private void setupDepartamentosActions() {
        btnDepartamentos.setOnAction(event -> tabPane.getSelectionModel().select(tabDepartemento));
        btnListarDepartementos.setOnAction(event -> tabPane.getSelectionModel().select(tabListarDepartamentos));
        btnRegistarDepartamento.setOnAction(event -> tabPane.getSelectionModel().select(tabAddDepartamento));
    }


}

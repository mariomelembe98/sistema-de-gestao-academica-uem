/**
 * Sample Skeleton for 'estudante.fxml' Controller Class
 */

package com.uem.sgnfx.Controllers.Estudante;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.*;

import com.uem.sgnfx.DAO.DisciplinaDAOImpl;
import com.uem.sgnfx.DAO.InscricaoDAOImpl;
import com.uem.sgnfx.DAO.SemestreDAOImpl;
import com.uem.sgnfx.LoginApplication;
import com.uem.sgnfx.Models.Disciplina;
import com.uem.sgnfx.Models.Estudante;
import com.uem.sgnfx.Models.Inscricao;
import com.uem.sgnfx.Models.Semestre;
import com.uem.sgnfx.Services.SessionManager;
import com.uem.sgnfx.Utils.HibernateUtil;
import com.uem.sgnfx.Validations.AlertMessage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.CheckListView;
import org.hibernate.SessionFactory;

public class EstudanteController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    private SessionFactory sessionFactory;

    @FXML // fx:id="btnAvaliacoes"
    private Button btnAvaliacoes; // Value injected by FXMLLoader

    @FXML // fx:id="btnInscricoes"
    private Button btnInscricoes; // Value injected by FXMLLoader

    @FXML // fx:id="btnMatriculas"
    private Button btnMatriculas; // Value injected by FXMLLoader

    @FXML // fx:id="btnMensalidades"
    private Button btnMensalidades; // Value injected by FXMLLoader

    @FXML
    private Button btnSair;

    @FXML
    private Label lblLoggedUserName;

    @FXML // fx:id="tabAvaliacoes"
    private Tab tabAvaliacoes; // Value injected by FXMLLoader

    @FXML // fx:id="tabInscricoes"
    private Tab tabInscricoes; // Value injected by FXMLLoader

    @FXML // fx:id="tabMatriculas"
    private Tab tabMatriculas; // Value injected by FXMLLoader

    @FXML // fx:id="tabMensalidades"
    private Tab tabMensalidades; // Value injected by FXMLLoader

    @FXML // fx:id="tabPane"
    private TabPane tabPane; // Value injected by FXMLLoader

    @FXML
    private Accordion accordionSemestres;

    private AlertMessage alertMessage;

    @FXML
    private ListView disciplinasCheckList;

    @FXML
    private CheckListView<String> disciplinasCheckListNova;

    private Map<String, Disciplina> disciplinaMap = new HashMap<>();

    private SessionManager sessionManager;
    private SemestreDAOImpl semestreDAO;
    private DisciplinaDAOImpl disciplinaDAO;
    private InscricaoDAOImpl inscricaoDAO;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

        this.semestreDAO = new SemestreDAOImpl(HibernateUtil.getSessionFactory());
        this.disciplinaDAO = new DisciplinaDAOImpl(HibernateUtil.getSessionFactory());
        this.semestreDAO = new SemestreDAOImpl(HibernateUtil.getSessionFactory());
        this.inscricaoDAO = new InscricaoDAOImpl(HibernateUtil.getSessionFactory());
        this.sessionManager = new SessionManager();
        this.alertMessage = new AlertMessage();

        carregarSemestres();
        carregarDisciplinasDoEstudanteLogado();
        carregarTodasDisciplinas();

        Estudante loggedInUser = SessionManager.getLoggedInEntity();

        if (loggedInUser == null) {
            lblLoggedUserName.textProperty().setValue("Bem-vindo");
        }else {
            lblLoggedUserName.setText("Bem-vindo, " + loggedInUser.getNome());
        }

        // Adicionando manipuladores de eventos para os botões
        btnAvaliacoes.setOnAction(event -> tabPane.getSelectionModel().select(tabAvaliacoes));
        btnInscricoes.setOnAction(event -> tabPane.getSelectionModel().select(tabInscricoes));
        btnMatriculas.setOnAction(event -> tabPane.getSelectionModel().select(tabMatriculas));
        btnMensalidades.setOnAction(event -> tabPane.getSelectionModel().select(tabMensalidades));

    }

    // Método para carregar os semestres e disciplinas
    private void carregarSemestres() {
        // Obtém a lista de semestres e disciplinas
        List<Semestre> semestres = semestreDAO.readAll();
        List<Disciplina> disciplinas = disciplinaDAO.readAll();

        // Para cada semestre, cria um TitledPane no Accordion
        for (Semestre semestre : semestres) {
            TitledPane semestrePane = new TitledPane();
            semestrePane.setText(semestre.getNome()); // Exemplo: "Primeiro Semestre - 1º ano"
            semestrePane.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");

            // Cria um Accordion para as disciplinas dentro de cada semestre
            Accordion accordionDisciplinas = new Accordion();

            // Filtra as disciplinas que pertencem ao semestre atual
            List<Disciplina> disciplinasDoSemestre = disciplinas.stream()
                    .filter(d -> Objects.equals(d.getSemestre().getId(), semestre.getId()))
                    .toList();

            for (Disciplina disciplina : disciplinasDoSemestre) {
                TitledPane disciplinaPane = new TitledPane();
                disciplinaPane.setText(disciplina.getDesignacao()); // Exemplo: "Introdução às TIC - 2024"

                // Cria conteúdo para a disciplina com informações adicionais
                VBox disciplinaContent = new VBox(5);
                disciplinaContent.getChildren().addAll(
                        new Label("Professor: Nome do Professor"),
                        new Label("Data de Inscrição: 2023-03-10"),
                        new Label("Média de Frequência: 10"),
                        new Label("Nota Final: 11"),
                        new Label("Estado: Aprovado")
                );

                // Define o conteúdo da disciplina
                disciplinaPane.setContent(disciplinaContent);
                accordionDisciplinas.getPanes().add(disciplinaPane);
            }

            // Define o Accordion de disciplinas como conteúdo do semestre
            accordionDisciplinas.setPrefHeight(60.0);
            accordionDisciplinas.setPrefWidth(780.0);
            semestrePane.setContent(accordionDisciplinas);
            accordionSemestres.getPanes().add(semestrePane);
        }
    }

    @FXML
    private void adicionarInscricoes() {
        // Obter o estudante logado
        Estudante estudanteLogado = SessionManager.getLoggedInEntity();

        // Verificar se há disciplinas selecionadas
        List<String> disciplinasSelecionadas = disciplinasCheckListNova.getCheckModel().getCheckedItems();
        if (disciplinasSelecionadas.isEmpty()) {
            alertMessage.showAlertWarning("Por favor, selecione pelo menos uma disciplina.");
            return;
        }

        for (String disciplinaString : disciplinasSelecionadas) {
            // Obter o objeto Disciplina correspondente da string selecionada
            Disciplina disciplina = disciplinaMap.get(disciplinaString);

            if (disciplina != null) {
                // Criar uma Inscricao para cada disciplina selecionada
                Inscricao inscricao = new Inscricao();
                inscricao.setEstudante(estudanteLogado);
                inscricao.setDisciplina(disciplina);
                inscricao.setCreatedAt(Instant.now());
                inscricao.setUpdatedAt(Instant.now());

                // Salvar a inscrição no banco de dados
                inscricaoDAO.create(inscricao);
            }
        }

        alertMessage.showAlertSuccess("Inscrições criadas com sucesso!");
        carregarDisciplinasDoEstudanteLogado();
    }



    public void carregarDisciplinasDoEstudanteLogado() {
        Estudante loggedInUser = SessionManager.getLoggedInEntity();
        Long estudanteId = loggedInUser.getId(); // Supondo que você tenha o estudante logado no sistema
        List<Disciplina> disciplinas = inscricaoDAO.buscarDisciplinasPorEstudante(estudanteId);

        // Limpa a CheckList
        disciplinasCheckList.getItems().clear();

        // Agora você pode exibir ou usar a lista de disciplinas como preferir
        disciplinas.forEach(disciplina -> disciplinasCheckList.getItems().add(disciplina.getDesignacao() + " | " + disciplina.getSemestre().getNome()));
    }

    private void carregarTodasDisciplinas() {
        List<Disciplina> disciplinas = disciplinaDAO.readAll();
        for (Disciplina disciplina : disciplinas) {
            // Formatação da string de exibição
            String displayText = disciplina.getDesignacao() + " | " + disciplina.getSemestre().getNome();

            // Adiciona a string ao CheckListView
            disciplinasCheckListNova.getItems().add(displayText);

            // Armazena a relação entre a string e o objeto Disciplina
            disciplinaMap.put(displayText, disciplina);
        }
    }

    @FXML
    private void sair() {

        // Mostrar uma caixa de diálogo de confirmação
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Saída");
        alert.setHeaderText("Tem certeza de que deseja sair?");

        // Aguardar resposta do utilizador
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Obtém a janela atual a partir do botão "Sair"
            Stage currentStage = (Stage) btnSair.getScene().getWindow();
            currentStage.close();

            // Abre a tela de login
            try {
                Stage loginStage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("tela-login.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 800, 500);
                loginStage.setScene(scene);
                loginStage.setResizable(false);
                loginStage.setTitle("Autenticação!");
                loginStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}

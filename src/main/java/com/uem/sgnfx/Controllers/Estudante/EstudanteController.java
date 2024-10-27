/**
 * Sample Skeleton for 'estudante.fxml' Controller Class
 */

package com.uem.sgnfx.Controllers.Estudante;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.uem.sgnfx.DAO.DisciplinaDAOImpl;
import com.uem.sgnfx.DAO.SemestreDAOImpl;
import com.uem.sgnfx.Models.Disciplina;
import com.uem.sgnfx.Models.Estudante;
import com.uem.sgnfx.Models.Semestre;
import com.uem.sgnfx.Services.SessionManager;
import com.uem.sgnfx.Utils.HibernateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
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
    private SessionManager sessionManager;
    private SemestreDAOImpl semestreDAO;
    private DisciplinaDAOImpl disciplinaDAO;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

        this.semestreDAO = new SemestreDAOImpl(HibernateUtil.getSessionFactory());
        this.disciplinaDAO = new DisciplinaDAOImpl(HibernateUtil.getSessionFactory());


        carregarSemestres();

        Estudante loggedInUser = SessionManager.getLoggedInEntity();

        if (loggedInUser != null) {
            lblLoggedUserName.setText("Bem-vindo, " + loggedInUser.getNome());
        }else {
            lblLoggedUserName.textProperty().setValue("Bem-vindo");
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





}

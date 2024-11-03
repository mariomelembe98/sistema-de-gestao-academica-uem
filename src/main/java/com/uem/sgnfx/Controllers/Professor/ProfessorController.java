package com.uem.sgnfx.Controllers.Professor;

/**
 * Created by USER on 19/09/2024.
 */

import com.jfoenix.controls.JFXButton;
import com.uem.sgnfx.DAO.AvaliacaoDAO;
import com.uem.sgnfx.DAO.DisciplinaDAOImpl;
import com.uem.sgnfx.DAO.EstudanteDAOImpl;
import com.uem.sgnfx.DAO.InscricaoDAOImpl;
import com.uem.sgnfx.LoginApplication;
import com.uem.sgnfx.Models.Avaliacao;
import com.uem.sgnfx.Models.Disciplina;
import com.uem.sgnfx.Models.Docente;
import com.uem.sgnfx.Models.Inscricao;
import com.uem.sgnfx.Services.SessionManager;
import com.uem.sgnfx.Utils.HibernateUtil;
import com.uem.sgnfx.Validations.AlertMessage;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    private JFXButton btnAvaliacoes;

    @FXML
    private JFXButton btnHome;

    @FXML
    private Button btnSair;

    @FXML
    private JFXButton btnTurmas;

    @FXML
    private Label lblLoggedUserName, panelDisciplinas;

    @FXML
    private Label panelDisciplinas1, lblNomeDisciplina, lblNomeDisciplina2;

    @FXML
    private Label panelTurmas;

    @FXML
    private Label panelTurmas1;

    @FXML
    private Tab tabDisciplina;

    @FXML
    private Tab tabDisciplinas;

    @FXML
    private Tab tabAvaliacoes;

    @FXML
    private Tab tabAvaliacao;

    @FXML
    private Tab tabHome;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabTurmas;

    @FXML
    private Tab tabTurmas1;

    @FXML
    private TextField txtDescricaoAvaliacao, txtPesoAvaliacao;

    @FXML
    private DatePicker txtDataAvaliacao;

    @FXML
    private AnchorPane disciplinasPane;

    @FXML
    private AnchorPane disciplinasPaneAvaliacoes;

    @FXML
    private TableView<Inscricao> estudanteTableView;

    @FXML
    private AlertMessage alertMessage;

    @FXML
    private TableColumn<Inscricao, String> inscricaoNumeroEstudanteColumn, inscricaoEstudanteColumn, inscricaoDisciplinaColumn;

    @FXML
    private TableColumn<Inscricao, Instant> inscricaoCreatedAtColumn, inscricaoUpdatedAtColumn;

    private Disciplina disciplinaSelecionada;

    private InscricaoDAOImpl inscricaoDAO;
    private DisciplinaDAOImpl disciplinaDAO;
    private EstudanteDAOImpl estudanteDAO;
    private AvaliacaoDAO avaliacaoDAO;

    @FXML
    void initialize() {

        this.inscricaoDAO = new InscricaoDAOImpl(HibernateUtil.getSessionFactory());
        this.disciplinaDAO = new DisciplinaDAOImpl(HibernateUtil.getSessionFactory());
        this.estudanteDAO = new EstudanteDAOImpl(HibernateUtil.getSessionFactory());
        this.avaliacaoDAO = new AvaliacaoDAO(HibernateUtil.getSessionFactory());
        this.alertMessage = new AlertMessage();

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
        btnAvaliacoes.setOnAction(event -> tabPane.getSelectionModel().select(tabAvaliacoes));

        carregarDisciplinasDinamicamente();
    }

    private void carregarEstudantesPorDisciplina(Long disciplinaId) {
        List<Inscricao> inscricoes = inscricaoDAO.listarInscricoesPorDisciplina(disciplinaId);
        atualizarTabelaInscricoes(inscricoes);
    }


    private void atualizarTabelaInscricoes(List<Inscricao> inscricoes) {
        ObservableList<Inscricao> inscricaoObservableList = FXCollections.observableArrayList(inscricoes);
        estudanteTableView.setItems(inscricaoObservableList);

        // Configurando colunas da tabela
        inscricaoNumeroEstudanteColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEstudante().getCodigoEstudante())
        );
        inscricaoEstudanteColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEstudante().getNome() + " " + cellData.getValue().getEstudante().getApelido())
        );
        inscricaoDisciplinaColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDisciplina().getCursoNome())
        );
        inscricaoCreatedAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        inscricaoUpdatedAtColumn.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));
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

                JFXButton btnAvaliacoe = new JFXButton();
                btnAvaliacoe.setPrefSize(130, 120);  // Definir tamanho
                btnAvaliacoe.setLayoutX(xOffset);  // Definir posição X
                btnAvaliacoe.setLayoutY(yOffset);  // Definir posição Y

                // Adicionar ícone no botão
                ImageView icon = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/icons8_book_96px.png"))));
                ImageView icon2 = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/icons8_book_96px.png"))));

                // Substitua pelo caminho correto
                icon.setFitHeight(120);
                icon.setFitWidth(130);
                btnDisciplina.setGraphic(icon);  // Adiciona o ícone ao botão
                btnDisciplina.setCursor(Cursor.HAND);

                // Substitua pelo caminho correto
                icon2.setFitHeight(120);
                icon2.setFitWidth(130);
                btnAvaliacoe.setGraphic(icon2);  // Adiciona o ícone ao botão
                btnAvaliacoe.setCursor(Cursor.HAND);

                // Ação ao clicar no botão
                btnDisciplina.setOnAction(event -> {
                    listarEstudantesPorDisciplina(disciplina);
                });

                btnAvaliacoe.setOnAction(event -> {
                    listarEstudantesPorDisciplina(disciplina);
                });

                // Criar um label com o nome da disciplina
                Label lblDisciplina = new Label(disciplina.getDesignacao());
                lblDisciplina.setFont(new Font("System Bold", 13));
                lblDisciplina.setLayoutX(xOffset);
                lblDisciplina.setLayoutY(yOffset + 130);  // Posicionado abaixo do botão

                Label lblDisciplinaAvaliacoes = new Label(disciplina.getDesignacao());
                lblDisciplinaAvaliacoes.setFont(new Font("System Bold", 13));
                lblDisciplinaAvaliacoes.setLayoutX(xOffset);
                lblDisciplinaAvaliacoes.setLayoutY(yOffset + 130);  // Posicionado abaixo do botão

                // Adicionar o botão e o label ao AnchorPane
                disciplinasPane.getChildren().addAll(btnDisciplina, lblDisciplina);
                disciplinasPaneAvaliacoes.getChildren().addAll(btnAvaliacoe, lblDisciplinaAvaliacoes);

                // Adiciona efeito de hover (alterando cor, borda, etc.)
                btnDisciplina.setOnMouseEntered(event -> {
                    btnDisciplina.setStyle("-fx-background-color: #E0E0E0;"); // cor de fundo ao passar o mouse
                });
                btnDisciplina.setOnMouseExited(event -> {
                    btnDisciplina.setStyle(""); // restaura o estilo original quando o mouse sai
                });

                btnDisciplina.setOnAction(event -> {
                    listarEstudantesPorDisciplina(disciplina);
                });

                // Adiciona efeito de hover (alterando cor, borda, etc.)
                btnAvaliacoe.setOnMouseEntered(event -> {
                    btnAvaliacoe.setStyle("-fx-background-color: #E0E0E0;"); // cor de fundo ao passar o mouse
                });
                btnAvaliacoe.setOnMouseExited(event -> {
                    btnAvaliacoe.setStyle(""); // restaura o estilo original quando o mouse sai
                });

                btnAvaliacoe.setOnAction(event -> {
                    criarAvaliacaoPorDisciplina(disciplina);
                });


                // Atualizar a posição do próximo botão/label
                xOffset += 200;  // Espaçamento horizontal

                // Verificar se precisa mover para a próxima linha
                if (xOffset + 130 > disciplinasPane.getPrefWidth()) {
                    xOffset = 50;  // Resetar para o começo da linha
                    yOffset += 180;  // Mover para a próxima linha
                }

                // Verificar se precisa mover para a próxima linha
                if (xOffset + 130 > disciplinasPaneAvaliacoes.getPrefWidth()) {
                    xOffset = 50;  // Resetar para o começo da linha
                    yOffset += 180;  // Mover para a próxima linha
                }
            }
        } else {
            System.out.println("Nenhum docente logado.");
        }
    }

    private void listarEstudantesPorDisciplina(Disciplina disciplina) {
        List<Inscricao> inscricoes = inscricaoDAO.getInscricoesPorDisciplina(disciplina.getId());
        atualizarTabelaInscricoes(inscricoes);

        // Actualizar o título da disciplina
        lblNomeDisciplina.setText(disciplina.getDesignacao());

        // Selecionar a tab de disciplina
        tabPane.getSelectionModel().select(tabDisciplina);
    }

    private void criarAvaliacaoPorDisciplina(Disciplina disciplina) {
        // Atualizar o título da disciplina para o contexto da nova avaliação
        lblNomeDisciplina2.setText(disciplina.getDesignacao());

        // Armazena a disciplina selecionada em uma variável de classe
        this.disciplinaSelecionada = disciplina;

        // Abre a aba de criação de avaliação
        tabPane.getSelectionModel().select(tabAvaliacao);
    }


    @FXML
    private void sair() {

        // Mostrar uma caixa de diálogo de confirmação
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Saída");
        alert.setHeaderText(null);
        alert.setContentText("Tem certeza de que deseja sair?");

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

    @FXML
    private void salvarAvaliacao() {
        if (disciplinaSelecionada == null) {
            System.out.println("Nenhuma disciplina selecionada.");
            return;
        }

        // Capturar os dados do formulário
        String descricao = txtDescricaoAvaliacao.getText();
        LocalDate dataRealizacao = txtDataAvaliacao.getValue();
        double peso;

        if (descricao == null || descricao.isEmpty() || dataRealizacao == null) {
            alertMessage.showAlertWarning("Por favor, preecha todos os campos");
            return;
        }

        try {
            peso = Double.parseDouble(txtPesoAvaliacao.getText());
        } catch (NumberFormatException e) {
            alertMessage.showAlertError("Peso inválido.");
            return;
        }

        // Criar uma instância de Avaliacao e preencher os campos
        Avaliacao novaAvaliacao = new Avaliacao();
        novaAvaliacao.setDescricao(descricao);
        novaAvaliacao.setDataRealizacao(dataRealizacao);
        novaAvaliacao.setDisciplina(disciplinaSelecionada);
        novaAvaliacao.setPeso(peso);
        novaAvaliacao.setDocente(SessionManager.getLoggedInEntity());  // Usuário logado como criador
        novaAvaliacao.setCreatedAt(Instant.now());
        //novaAvaliacao.setUpdatedAt(Instant.now());

        // Salvar a nova avaliação no banco de dados
        try {
            avaliacaoDAO.create(novaAvaliacao);
            alertMessage.showAlertSuccess("Avaliação salva com sucesso para a disciplina: " + disciplinaSelecionada.getDesignacao());

            // Limpar o formulário
            txtDescricaoAvaliacao.clear();
            txtDataAvaliacao.setValue(null);
            txtPesoAvaliacao.clear();
        } catch (Exception e) {
            alertMessage.showAlertWarning("Erro ao salvar a avaliação: " + e.getMessage());
        }
    }


}

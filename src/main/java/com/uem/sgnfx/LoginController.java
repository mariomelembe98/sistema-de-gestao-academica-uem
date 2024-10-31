package com.uem.sgnfx;

import com.uem.sgnfx.Controllers.Admin.AdminApplication;

import java.net.URL;
import java.util.ResourceBundle;

import com.uem.sgnfx.Controllers.Estudante.EstudanteApplication;
import com.uem.sgnfx.Controllers.Professor.ProfessorApplication;
import com.uem.sgnfx.DAO.UserDAOImpl;
import com.uem.sgnfx.Models.Docente;
import com.uem.sgnfx.Models.Estudante;
import com.uem.sgnfx.Models.User;
import com.uem.sgnfx.Services.LoginService;
import com.uem.sgnfx.Services.SessionManager;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblEsqueciSenha;

    @FXML
    private ImageView iconVoltar;

    @FXML
    private Label lblVoltar;

    @FXML
    private Pane panelVoltar;

    @FXML
    private Tab tabEsqueciSenha;

    @FXML
    private Tab tabLogin;

    @FXML
    private TabPane tabPane;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Label lblMessage;

    @FXML
    private TextField txtUsuario;
    private SessionFactory sessionFactory;

    private final UserDAOImpl userDAO = new UserDAOImpl(this.sessionFactory);

    @FXML
    public void handleLogin(MouseEvent event) {
        String email = txtUsuario.getText();
        String password = txtSenha.getText();

//        if (email.isEmpty() || password.isEmpty()) {
//            lblMessage.setText("Preencha todos os campos.");
//            return;
//        }

        // Instancia o serviço de ‘login’
        LoginService loginService = new LoginService();

        // Tentativa de login para diferentes tipos de utilizadores
        User user = loginService.login(User.class, email, password);
        //User user = loginService.login(User.class, "admin@gmail.com", "12345");
        Estudante estudante = loginService.login(Estudante.class, email, password);
        Docente docente = loginService.login(Docente.class, email, password);

        // Verifica o tipo de utilizador logado e abre o painel correspondente
        if (user != null || estudante != null || docente != null) {
            if (user instanceof User) {
                // Armazena o utilizador na sessão
                SessionManager.setLoggedInEntity(user);
                abrirPainel(AdminApplication.class);
            } else if (docente instanceof Docente) {
                // Armazena o docente na sessão
                SessionManager.setLoggedInEntity(docente);
                abrirPainel(ProfessorApplication.class);
            } else {
                // Armazena o estudante na sessão
                SessionManager.setLoggedInEntity(estudante);
                abrirPainel(EstudanteApplication.class);
            }
        } else {
            lblMessage.setText("Credenciais inválidas!");
        }

    }


    // TODO: Método para abrir a aplicação AdminApplication
    private <T extends Application> void abrirPainel(Class<T> applicationClass) {
        try {
            // Fechar a janela de login
            Stage stageAtual = (Stage) btnLogin.getScene().getWindow();
            stageAtual.close();

            // Iniciar a aplicação passada como parâmetro
            T appInstance = applicationClass.getDeclaredConstructor().newInstance(); // Instancia a classe passada
            Stage novaStage = new Stage(); // Nova stage para a aplicação genérica
            appInstance.start(novaStage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        btnLogin.setOnMouseClicked(this::handleLogin);
        lblVoltar.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabLogin));
        iconVoltar.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabLogin));
        panelVoltar.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabLogin));
        lblEsqueciSenha.setOnMouseClicked(event -> tabPane.getSelectionModel().select(tabEsqueciSenha));
    }


}
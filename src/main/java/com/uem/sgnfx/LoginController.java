package com.uem.sgnfx;

import com.uem.sgnfx.Controllers.Admin.AdminApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.uem.sgnfx.DAO.UserDAOImpl;
import com.uem.sgnfx.Models.User;
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

import javax.swing.*;

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

        if (email.isEmpty() || password.isEmpty()) {
            lblMessage.setText("Preencha todos os campos.");
            return;
        }

        User user = userDAO.login(email, password);

        if (user != null) {
            lblMessage.setText("Login bem-sucedido!");
            // Carregar uma nova cena
            abrirAdminPanel();
        } else {
            lblMessage.setText("Credenciais inválidas!");
        }
    }


    // TODO: Método para abrir a aplicação AdminApplication
    private void abrirAdminPanel() {
        try {
            // TODO: Fechar a janela de login
            Stage stageAtual = (Stage) btnLogin.getScene().getWindow();
            stageAtual.close();

            // TODO: Iniciar a aplicação AdminApplication
            AdminApplication adminApp = new AdminApplication();
            Stage novaStage = new Stage(); // TODO: Nova stage para a aplicação Admin
            adminApp.start(novaStage);

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
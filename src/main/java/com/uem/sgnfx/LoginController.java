package com.uem.sgnfx;

import com.uem.sgnfx.Controllers.Admin.AdminApplication;
import java.net.URL;
import java.util.ResourceBundle;
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
    private TextField txtUsuario;

    @FXML
    void handleLogin(MouseEvent event) {
        String usuario = txtUsuario.getText();
        String senha = txtSenha.getText();

        if (usuario.isEmpty() && senha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!");
            return;
        }

        // TODO: Lógica para verificar credenciais de login
        if (usuario.equals("admin") || usuario.equals("email@gmail.com") && senha.equals("12345")) {
            System.out.println("Login bem-sucedido! " + txtUsuario.getText());
            abrirAdminPanel();
        } else {
            //Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Credenciais inválidas. Tente novamente.");

            JOptionPane.showMessageDialog(null, "Usuario ou senha incorreto!");
            //System.out.println("Nome de usuário ou senha incorretos!");
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
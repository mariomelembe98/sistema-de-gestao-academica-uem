/**
 * Sample Skeleton for 'estudante.fxml' Controller Class
 */

package com.uem.sgnfx.Controllers.Estudante;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

        // Adicionando manipuladores de eventos para os botões
        btnAvaliacoes.setOnAction(event -> tabPane.getSelectionModel().select(tabAvaliacoes));
        btnInscricoes.setOnAction(event -> tabPane.getSelectionModel().select(tabInscricoes));
        btnMatriculas.setOnAction(event -> tabPane.getSelectionModel().select(tabMatriculas));
        btnMensalidades.setOnAction(event -> tabPane.getSelectionModel().select(tabMensalidades));

    }



}

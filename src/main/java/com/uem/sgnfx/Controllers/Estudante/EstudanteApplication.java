package com.uem.sgnfx.Controllers.Estudante;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EstudanteApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/uem/sgnfx/Estudante/estudante.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1250, 700);
        primaryStage.setResizable(false);
        primaryStage.resizableProperty();
        primaryStage.setTitle("Estudante");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

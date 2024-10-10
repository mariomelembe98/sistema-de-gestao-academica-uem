package com.uem.sgnfx.Controllers.Professor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProfessorApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Use caminho absoluto se necessário
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/uem/sgnfx/Professor/professor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        primaryStage.setResizable(false);
        primaryStage.resizableProperty();
        primaryStage.setTitle("Painel do Professor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

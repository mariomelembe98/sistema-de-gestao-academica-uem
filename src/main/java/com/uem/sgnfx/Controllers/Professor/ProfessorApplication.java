package com.uem.sgnfx.Controllers.Professor;

import com.uem.sgnfx.Validations.AlertMessage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProfessorApplication extends Application {

    private final AlertMessage alertMessage = new AlertMessage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Use caminho absoluto se necessário
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/uem/sgnfx/Professor/professor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        primaryStage.setResizable(false);
        primaryStage.resizableProperty();
        primaryStage.setTitle("Painel do Professor");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            alertMessage.close(primaryStage);
        });
    }
}

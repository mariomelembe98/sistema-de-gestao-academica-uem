package com.uem.sgnfx.Controllers.Admin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Use caminho absoluto se necessário
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/uem/sgnfx/Admin/admin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        primaryStage.resizableProperty();
        primaryStage.setTitle("Admin Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

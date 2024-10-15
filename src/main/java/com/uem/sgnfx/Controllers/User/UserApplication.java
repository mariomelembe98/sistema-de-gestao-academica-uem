package com.uem.sgnfx.Controllers.User;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UserApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/uem/sgnfx/User/user.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 600);
        primaryStage.setResizable(false);
        primaryStage.resizableProperty();
        primaryStage.setTitle("Painel de Utilizadores");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

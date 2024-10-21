package com.uem.sgnfx.Validations;

import javafx.scene.control.Alert;

/**
 * Created by USER on 21/10/2024.
 */

public class AlertMessage {

    public void showAlertInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}

package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import util.AuthManager;
import main.CalendarApp;

public class RegisterController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    protected void onRegister(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (AuthManager.register(username, password)) {
            showAlert("Registration successful", "You can now login.");
            try {
                CalendarApp.changeScene("login.fxml", 400, 300);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Registration failed", "Username already exists.");
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
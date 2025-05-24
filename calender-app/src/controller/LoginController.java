package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import util.AuthManager;
import main.CalendarApp;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    protected void onLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (AuthManager.login(username, password)) {
            AuthManager.setCurrentUser(username);
            try {
                CalendarApp.changeScene("dashboard.fxml", 600, 400);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Login failed", "Invalid username or password");
        }
    }

    @FXML
    protected void onRegisterRedirect(ActionEvent event) {
        try {
            CalendarApp.changeScene("register.fxml", 400, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

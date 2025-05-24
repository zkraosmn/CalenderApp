package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class CalendarApp extends Application {
    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 300);
        stage.setTitle("Calendar Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void changeScene(String fxml, int width, int height) throws IOException {
        FXMLLoader loader = new FXMLLoader(CalendarApp.class.getResource("/view/" + fxml));
        primaryStage.setScene(new Scene(loader.load(), width, height));
    }

    public static void main(String[] args) {
        launch();
    }
}
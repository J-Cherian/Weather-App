package org.example.weatherapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

// Main class to launch the JavaFX application
public class main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("startView.fxml")));

            Scene scene = new Scene(root);
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/icon.png")));
            stage.getIcons().add(icon);  // Set the window icon
            stage.setTitle("Weather App");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
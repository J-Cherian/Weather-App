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
            // Load the FXML file that defines the layout for the start view (startView.fxml)
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("startView.fxml")));

            // Create a new Scene using the loaded layout (root)
            Scene scene = new Scene(root);

            // Load and set the application icon from the resources (icon.png)
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/icon.png")));
            stage.getIcons().add(icon);  // Set the window icon

            // Set the title of the main application window (stage)
            stage.setTitle("Weather App");

            // Set the Scene on the Stage (the window) and display it
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            // Print any exceptions that occur during the loading of the FXML or setting of the stage
            e.printStackTrace();
        }
    }

    // The main method that launches the JavaFX application
    public static void main(String[] args) {
        launch();  // Calls the JavaFX Application's launch method, which starts the application
    }
}
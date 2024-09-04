package org.example.weatherapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class startViewController {

    @FXML
    private TextField cityTextField;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void enterCity(ActionEvent event) throws IOException {

        String city = cityTextField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/displayView.fxml"));
        root = loader.load();

        displayController displayController = loader.getController();

        displayController.displayCity(city);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
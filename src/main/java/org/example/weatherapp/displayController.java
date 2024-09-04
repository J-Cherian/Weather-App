package org.example.weatherapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * The displayController class manages the display of weather data in the application.
 * It sets the weather information (city, description, temperature, humidity, wind speed) on the UI components
 * and handles the image display for corresponding weather conditions.
 */

public class displayController {
    @FXML
    private Label cityLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private ImageView weatherImageView;
    @FXML
    private Pane myPane;
    @FXML
    private Label temperatureLabel;
    @FXML
    private Label humidityLabel;
    @FXML
    private Label windSpeedLabel;
    @FXML
    private ImageView humidityImageView;
    @FXML
    private ImageView windSpeedImageView;

    /**
     * This method is called when the view is first loaded. It sets up the default icons for humidity and wind speed.
     */
    @FXML
    public void initialize() {
        Image humidityImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/example/weatherapp/images/humidity.png")));
        humidityImageView.setImage(humidityImage);

        Image windSpeedImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/example/weatherapp/images/windspeed.png")));
        windSpeedImageView.setImage(windSpeedImage);
    }

    // Methods to display elements
    public void displayCity(String city) {
        cityLabel.setText(city);
    }

    public void displayDescriptionAndImage(String description, String iconCode) {
        descriptionLabel.setText(description);
        displayWeatherImage(description, iconCode);
    }

    public void displayTemperature(double tempCelsius) {
        String temperatureText = String.format("%.1f°C", tempCelsius);
        temperatureLabel.setText(temperatureText);
    }

    public void displayHumidity(int humidity) {
        humidityLabel.setText("Humidity: " + humidity + "%");
    }

    public void displayWindSpeed(double windSpeedMph) {
        windSpeedLabel.setText(String.format("Wind Speed: %.1f mph", windSpeedMph));
    }

    // Displays the appropriate weather icon based on the weather description
    private void displayWeatherImage(String weatherDescription, String iconCode) {
        String imagePath = "";
        weatherDescription = weatherDescription.toLowerCase();
        boolean isNight = iconCode.endsWith("n");
        // sets the background
        setGradientBasedOnTime(isNight);


        // matches the correct image based on weather condition
        if (weatherDescription.contains("clouds")) {
            imagePath = isNight ? "/org/example/weatherapp/images/cloudy-night.png" : "/org/example/weatherapp/images/cloudy-day.png";
        } else if (weatherDescription.equals("clear sky")) {
            imagePath = isNight ? "/org/example/weatherapp/images/night.png" : "/org/example/weatherapp/images/sun.png";
        } else if (weatherDescription.contains("rain")) {
            imagePath = "/org/example/weatherapp/images/rain.png";
        } else if (weatherDescription.contains("thunder")) {
            imagePath = "/org/example/weatherapp/images/storm.png";
        } else if (weatherDescription.contains("snow")) {
            imagePath = "/org/example/weatherapp/images/snow.png";
        } else {
            imagePath = "/org/example/weatherapp/images/icon.png";
        }

        // display the weather icon with an effect
        try {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            if (image.isError()) {
                System.out.println("Error loading image: " + imagePath);
            } else {
                weatherImageView.setImage(image);
                weatherImageView.setEffect(new DropShadow(10, Color.BLACK));
            }
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
    }

    // method to set the background using css
    public void setGradientBasedOnTime(boolean isNight) {
        if (isNight) {
            myPane.getStyleClass().add("night-gradient");
        } else {
            myPane.getStyleClass().add("day-gradient");
        }
    }

    // Handles the action of returning to the main view when the "Back" button is clicked
    @FXML
    public void backToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("startView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
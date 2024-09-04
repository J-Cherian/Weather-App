package org.example.weatherapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The startViewController class handles user interaction with the initial view, allowing the user to input a city name
 * and fetch weather data for that city from the OpenWeather API. If the city is valid, the controller switches to
 * a new scene showing weather information.
 */
public class startViewController {

    @FXML
    private TextField cityTextField;
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Method triggered when the user clicks the submit button. It fetches weather data from the OpenWeather API
     * based on the city name provided in the text field.
     */
    public void enterCity(ActionEvent event) throws IOException {

        String city = cityTextField.getText();

        // Add your OpenWeather API key here. Replace with your actual API key.
        // You need to make an account at https://openweathermap.org to get an API key.
        String apiId = "2a333a5ceb43fa33c5de26331a6fd4dd";

        // Check if the API key is missing or invalid
        if (apiId == null || apiId.isEmpty()) {
            showErrorAlert("API Key Missing", "Please provide a valid API key.");
            return;
        }

        // Construct the URL for the Geo API to get latitude and longitude of the city
        String geoApiUrl = "http://api.openweathermap.org/geo/1.0/direct?appid=" + apiId + "&q=" + city;
        URL url = new URL(geoApiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read the response from the Geo API
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder content = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        connection.disconnect();

        JSONArray geoJsonArray = new JSONArray(content.toString());

        // Check if the API response contains any data
        if (geoJsonArray.length() > 0) {

            JSONObject geoJsonObject = geoJsonArray.getJSONObject(0);
            String lat = String.valueOf(geoJsonObject.getDouble("lat"));  // Extract latitude
            String lon = String.valueOf(geoJsonObject.getDouble("lon"));  // Extract longitude

            // URL for the weather API using the latitude and longitude
            String weatherApiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + apiId;
            URL weatherUrl = new URL(weatherApiUrl);
            HttpURLConnection weatherConnection = (HttpURLConnection) weatherUrl.openConnection();
            weatherConnection.setRequestMethod("GET");

            // Read the weather API response
            BufferedReader weatherIn = new BufferedReader(new InputStreamReader(weatherConnection.getInputStream()));
            StringBuilder weatherContent = new StringBuilder();

            while ((inputLine = weatherIn.readLine()) != null) {
                weatherContent.append(inputLine);
            }
            weatherIn.close();
            weatherConnection.disconnect();

            JSONObject weatherJsonObject = new JSONObject(weatherContent.toString());

            // Extract weather description and icon code from the JSON response
            String weatherDescription = weatherJsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
            String iconCode = weatherJsonObject.getJSONArray("weather").getJSONObject(0).getString("icon");

            // Extract temperature and humidity
            JSONObject mainData = weatherJsonObject.getJSONObject("main");
            double tempKelvin = mainData.getDouble("temp");  // Temperature in Kelvin
            int humidity = mainData.getInt("humidity");  // Humidity percentage

            // Extract wind speed
            JSONObject windData = weatherJsonObject.getJSONObject("wind");
            double windSpeedMs = windData.getDouble("speed");  // Wind speed in meters/second

            // Convert temperature from Kelvin to Celsius
            double tempCelsius = tempKelvin - 273.15;
            // Convert wind speed from meters/second to miles/hour
            double windSpeedMph = windSpeedMs * 2.23694;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("apiCallView.fxml"));
            Parent root = loader.load();

            displayController display = loader.getController();
            display.displayCity(city);
            display.displayDescriptionAndImage(weatherDescription, iconCode);
            display.displayTemperature(tempCelsius);
            display.displayHumidity(humidity);
            display.displayWindSpeed(windSpeedMph);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {
            showErrorAlert("City Not Found", "The city '" + city + "' could not be found. Please check the city name.");
        }
    }

    // Displays an error alert
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

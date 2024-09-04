## Weather App
A JavaFX-based weather application that provides real-time weather information for a user-specified city. The application dynamically displays key weather metrics, including temperature, humidity, wind speed, and a brief weather description. Weather data is retrieved from a public API in JSON format, which is parsed and displayed in the user interface to deliver a seamless experience.

## Features
- Real-time Weather Data: Displays live weather conditions for any city entered by the user.
- User-Friendly Interface: Built with JavaFX and FXML, offering a clean and responsive interface.
- Weather Metrics: Shows temperature, humidity, wind speed, and a brief weather description.
- Dynamic Themes: Changes background gradients based on the time of day (day/night) in the selected city.
- API Integration: Connects to a weather API to fetch real-time weather data in JSON format.

## Start view
![image](https://github.com/user-attachments/assets/76ece8ec-7cff-434e-ab1c-bd81eb5698e3)  
The main screen allows the user to enter the city name for which they want to see the weather.  

## Weather Details view
![image](https://github.com/user-attachments/assets/fd607e08-7880-4aa2-9336-e787743d02a0)
![image](https://github.com/user-attachments/assets/e55f8365-e6f0-44b7-83eb-bb8a1b21697b)  
After submitting the city name, the application displays the current weather data for the selected city.  
CSS has been used to show if its day or night using a colour gradient.  

## Setup
1. **Clone the repository:**
  ```bash   
  git clone https://github.com/J-Cherian/Weather-App.git 
  cd weatherapp
  ```
3. Make sure you have JavaFX SDK installed
   
  > If not Install JavaFX: Download the JavaFX SDK from https://gluonhq.com/products/javafx/ and configure it in your IDE.

4. **Get an OpenWeather API Key:**
   
  > The application uses the OpenWeather API to fetch live weather data. To use the API, you'll need an API key.  
  > Go to the OpenWeather website https://openweathermap.org/  
  > Sign up for a free account (or log in if you already have one).  
  > Navigate to the "API Keys" section in your profile.  
  > Create a new API key.  
  > Copy the API key for use in the project.  
  > Add the API Key to the Application: Once you have the API key, you will need to modify the API request in the application to include your key.  
  > Locate the part of the code where the weather API is called which is in startViewController.java, and replace YOUR_API_KEY with your actual API key:  

  ```java
  String apiKey = "YOUR_API_KEY";
  ```

4. **Build and Run the Project:**
   
  > Open the project in your preferred IDE (IntelliJ IDEA, Eclipse or any other ide).    
  > Ensure that the JavaFX SDK is properly configured.  
  > Run the application from the WeatherApp main class.  

## Contributing
Contributions are welcome! Please submit a pull request with your changes or open an issue if you find a bug.

module org.example.weatherapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;

    opens org.example.weatherapp to javafx.fxml;
    exports org.example.weatherapp;
}
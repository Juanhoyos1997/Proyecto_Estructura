module com.example.basesdedatos {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.basesdedatos to javafx.fxml;
    exports com.example.basesdedatos;
}
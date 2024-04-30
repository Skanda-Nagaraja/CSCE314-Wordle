module com.example.wordle {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.wordle to javafx.fxml;
    exports com.example.wordle;
}
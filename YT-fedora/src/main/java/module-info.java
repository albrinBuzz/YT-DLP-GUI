module com.example.ytfedora {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ytfedora to javafx.fxml;
    exports com.example.ytfedora;
}
module com.example.exceptionsiojavafxquiz {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.exceptionsiojavafxquiz to javafx.fxml;
    exports com.example.exceptionsiojavafxquiz;
}
module gavinchen.metadot {
    requires javafx.controls;
    requires javafx.fxml;


    opens gavinchen.metadot to javafx.fxml;
    exports gavinchen.metadot;
}
module gavinchen.distancedemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens gavinchen.distancedemo to javafx.fxml;
    exports gavinchen.distancedemo;
}
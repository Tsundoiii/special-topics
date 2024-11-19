module gavinchen.imagedemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens gavinchen.imagedemo to javafx.fxml;
    exports gavinchen.imagedemo;
}
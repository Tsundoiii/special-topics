module gavinchen.cheeseburgermenu {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens gavinchen.cheeseburgermenu to javafx.fxml;
    exports gavinchen.cheeseburgermenu;
}
module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;

    opens lab to javafx.fxml;
    exports lab;
}

module main {
    requires javafx.controls;
    requires javafx.fxml;

    opens exam to javafx.fxml;
    exports exam;
}

module main {
    requires javafx.controls;
    requires javafx.fxml;

    opens windmill to javafx.fxml;
    exports windmill;
}

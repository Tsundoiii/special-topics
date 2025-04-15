module jgoodman.GraphicsDemo2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens jgoodman.GraphicsDemo2 to javafx.fxml;
    exports jgoodman.GraphicsDemo2;
}

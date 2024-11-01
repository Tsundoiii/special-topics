package gavinchen.distancedemo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrimaryController {
    @FXML
    private Label outputLabel;
    @FXML
    private TextField x1, y1, x2, y2;

    @FXML
    private void calcListener() {
        double x1in = Double.parseDouble(x1.getText());
        double x2in = Double.parseDouble(x2.getText());
        double y1in = Double.parseDouble(y1.getText());
        double y2in = Double.parseDouble(y2.getText());

        double solution = Math.sqrt(Math.pow(x2in - x1in, 2) + Math.pow(y2in - y1in, 2));

        outputLabel.setText(Double.toString(solution));
    }
}
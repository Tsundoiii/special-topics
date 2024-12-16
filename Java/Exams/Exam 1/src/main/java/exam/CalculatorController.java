package exam;

/**
 * This template is provided to aid completion of Exam 1.
 * Sharing this template is a violation of academic honesty.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Complete the TransformerController
 * You may open transformer.fxml in SceneBuilder
 * to examine the fx:id's of controls.
 * But you may not modify the GUI.
 */

public class CalculatorController {
    /**
     * Running the app initially will throw errors because
     * expected events are missing in this controller file.
     */

    @FXML
    private TextField quantity;

    @FXML
    private Label totalCost;

    @FXML
    private TextField unitPrice;

    @FXML
    void initialize() {
        unitPrice.setText("3.0");
        quantity.setText("0");
    }

    @FXML
    void computeCost(ActionEvent event) {
        double price = Double.parseDouble(unitPrice.getText());
        int orderedQuantity = Integer.parseInt(quantity.getText());

        totalCost.setText("$" + price * orderedQuantity);
    }
}

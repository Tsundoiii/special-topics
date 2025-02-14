package gavinchen.cheeseburgermenu;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class Controller {
    @FXML
    private RadioButton beef;

    @FXML
    private Button getTotal;

    @FXML
    private CheckBox lettuce;

    @FXML
    private CheckBox mayo;

    @FXML
    private CheckBox mustard;

    @FXML
    private CheckBox onion;

    @FXML
    private TextField patties;

    @FXML
    private CheckBox tomato;

    @FXML
    private Text total;

    @FXML
    private RadioButton vegan;

    @FXML
    private void calculateTotal() {
        int numPatties = Integer.parseInt(patties.getText());
        boolean isBeefPatty = beef.isSelected();
        int numToppings = 0;
        
        if (tomato.isSelected()) {
            numToppings++;
        }
        
        if (lettuce.isSelected()) {
            numToppings++;
        }
        
        if (onion.isSelected()) {
            numToppings++;
        }
        
        if (mayo.isSelected()) {
            numToppings++;
        }
        
        if (mustard.isSelected()) {
            numToppings++;
        }

        double pattiesCost = (numPatties - 1) * 2 + (isBeefPatty ? 0 : 1);
        double toppingsCost = numToppings <= 3 ? 0 : (numToppings - 3) * 0.5;
        double totalCost = pattiesCost + toppingsCost + 5;

        total.setText(String.format("Total: $%.2f", totalCost));

        patties.setText("1");
        beef.setSelected(true);
        vegan.setSelected(false);

        tomato.setSelected(false);
        lettuce.setSelected(false);
        onion.setSelected(false);
        mayo.setSelected(false);
        mustard.setSelected(false);
    }

}

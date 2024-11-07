package lab;

/**
 * Name: Gavin Chen
 * Username: gavinchen
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Stream;

public class Controller {
    @FXML
    private VBox bottom;

    @FXML
    private ToggleGroup colors;

    @FXML
    private RadioButton cyan;

    @FXML
    private Button diverge;

    @FXML
    private CheckBox east;

    @FXML
    private VBox left;

    @FXML
    private CheckBox north;

    @FXML
    private RadioButton orange;

    @FXML
    private VBox right;

    @FXML
    private RadioButton salmon;

    @FXML
    private CheckBox south;

    @FXML
    private RadioButton springGreen;

    @FXML
    private VBox top;

    @FXML
    private CheckBox west;

    @FXML
    private TextField words;

    private HashMap<CheckBox, VBox> toLocations= new HashMap<>();

    private HashMap<RadioButton, String> colorStrings = new HashMap<>();

    public Controller() {

    }

    @FXML
    void colorChange(MouseEvent event) {
        toLocations.put(north, top);
        toLocations.put(south, bottom);
        toLocations.put(east, right);
        toLocations.put(west, left);

        colorStrings.put(salmon, "salmon");
        colorStrings.put(springGreen, "SpringGreen");
        colorStrings.put(orange, "Orange");
        colorStrings.put(cyan, "Cyan");

        Iterator<CheckBox> locationsIterator = Stream.of(north, south, east, west).iterator();
        while (locationsIterator.hasNext()) {
            CheckBox location = locationsIterator.next();
            if (location.isSelected()) {
                toLocations.get(location).setStyle("-fx-background-color: " + colorStrings.get(event.getSource()));
            }
        }
    }

    @FXML
    void diverge(ActionEvent event) {
        String[] input = words.getText().split(" ");
        VBox[] locations = {top, right, bottom, left};

        if (input.length != 4) {
            words.clear();
            words.setPromptText("Please enter exactly 4 words");
            return;
        }

        for (int i = 0; i < 4; i++) {
            Text text = new Text();
            text.setText(input[i]);

            locations[i].getChildren().add(text);
        }

    }

}

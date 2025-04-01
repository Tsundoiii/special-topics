package lab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class Controller {

    @FXML
    private MenuItem extra;

    @FXML
    private Label instructions;

    @FXML
    private VBox main;

    @FXML
    private MenuItem normal;

    @FXML
    private Button play;

    @FXML
    private Label youWin;

    @FXML
    void initialize() {

    }

    @FXML
    void startGame(ActionEvent event) {
        youWin.setVisible(false);
        play.setVisible(false);
        instructions.setVisible(false);

        for (int i = 0; i < 7; i++) {
            main.getChildren().add(new RandomCircle(main));
        }
    }
}

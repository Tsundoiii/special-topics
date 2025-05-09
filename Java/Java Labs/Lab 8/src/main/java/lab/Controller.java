package lab;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.concurrent.ThreadLocalRandom;

public class Controller {

    private final RandomCircle[] circles = new RandomCircle[7];
    @FXML
    private MenuItem extra;
    @FXML
    private Label instructions;
    @FXML
    private Pane main;
    @FXML
    private MenuItem normal;
    @FXML
    private Button play;
    @FXML
    private Label youWin;
    @FXML
    private VBox vbox;


    @FXML
    void initialize() {
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(new Duration(500), event -> {
            for (RandomCircle circle : circles) {
                if (!circle.captured) {
                    return;
                }
            }

            vbox.toFront();
            youWin.setVisible(true);
            play.setText("Play again");
            play.setVisible(true);
            instructions.setVisible(true);
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    void startGame(ActionEvent event) {
        youWin.setVisible(false);
        play.setVisible(false);
        instructions.setVisible(false);

        for (RandomCircle circle : circles) {
            main.getChildren().remove(circle);
        }

        for (int i = 0; i < circles.length; i++) {
            circles[i] = new RandomCircle(main.getWidth(), main.getHeight(), ThreadLocalRandom.current().nextInt(10, 100));
            main.getChildren().add(circles[i]);
        }
    }

}

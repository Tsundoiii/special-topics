package windmill;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.animation.*;
import javafx.animation.Animation.Status;
import javafx.util.Duration;

public class WindmillController {

    @FXML
    private Pane pane;
    
    private RotateTransition rotate1;
    private RotateTransition rotate2;
    private int rotateTime = 1500;
    
    private FillTransition lightFlicker;
    private int flickerTime = 3000;

    /**
     * Creates all shapes on the pane and creates/plays all animations
     */
    @FXML
    public void initialize() {
    	//Create ground
    	Rectangle ground = new Rectangle(0, 520, 400, 80);
        ground.setFill(Color.GREEN);

        //Create tower
        Polygon tower = new Polygon(120, 550, 280, 550, 230, 150, 170, 150);
        tower.setFill(Color.RED);
        

        //Create two blades
        Rectangle blade1 = new Rectangle(190, 50, 20, 280);
        blade1.setFill(Color.GRAY);
        Rectangle blade2 = new Rectangle(190, 50, 20, 280);
        blade2.setFill(Color.GRAY);
        

        //Create door
        Arc door = new Arc(200, 550, 30, 70, 0, 180);
        door.setFill(Color.BLACK);
        door.setType(ArcType.ROUND);
        

        //Create window
        Circle window = new Circle(200, 340, 20);
        window.setFill(Color.BLACK);
        

        //Create first blade rotation animation
        rotate1 = new RotateTransition(Duration.millis(rotateTime), blade1);
        rotate1.setFromAngle(0);
        rotate1.setByAngle(360);
        rotate1.setCycleCount(Animation.INDEFINITE);
        rotate1.setInterpolator(Interpolator.LINEAR);
        rotate1.play();
        

        //Create second blade rotation animation
        rotate2 = new RotateTransition(Duration.millis(rotateTime), blade2);
        rotate2.setFromAngle(90);
        rotate2.setByAngle(360);
        rotate2.setCycleCount(Animation.INDEFINITE);
        rotate2.setInterpolator(Interpolator.LINEAR);
        rotate2.play();
        
        
        //Create window light animation
        lightFlicker = new FillTransition(Duration.millis(flickerTime), window, Color.BLACK, Color.YELLOW);
        lightFlicker.setCycleCount(Animation.INDEFINITE);
        lightFlicker.setAutoReverse(true);
        lightFlicker.play();
        
        
        //Display all shapes
        pane.getChildren().addAll(ground, tower, blade1, blade2, door, window);
        
    }
    
    /**
     * Reverses the rotation direction of the windmill blades
     * Already set as a mouse press event on the pane
     */
    @FXML
    public void toggleRotate(MouseEvent e) {
    	rotate1.stop();
        rotate2.stop();
        rotate1.setFromAngle(rotate1.getNode().getRotate());
        rotate2.setFromAngle(rotate2.getNode().getRotate());
        rotate1.setByAngle(-rotate1.getByAngle());
        rotate2.setByAngle(-rotate2.getByAngle());
        rotate1.play();
        rotate2.play();
    }
    
    /**
     * Pauses or resumes all the animations if the SPACE key is pressed
     * Already set as a key press event on the pane
     * Can use .getStatus() on a transition to see if
     * it is one of the possible values:
     * Status.RUNNING, Status.PAUSED, or Status.STOPPED
     */
    @FXML
    public void togglePlay(KeyEvent e) {
    	if (e.getCode() == KeyCode.SPACE) {
            switch (rotate1.getStatus()) {
                case RUNNING -> {
                    rotate1.stop();
                    rotate2.stop();
                }
                case STOPPED -> {
                    rotate1.setFromAngle(rotate1.getNode().getRotate());
                    rotate2.setFromAngle(rotate2.getNode().getRotate());
                    rotate1.play();
                    rotate2.play();
                }
                case PAUSED -> {}
            }
        }
    }
}

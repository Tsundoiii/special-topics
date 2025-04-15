package jgoodman.GraphicsDemo2;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class PrimaryController {
	
	@FXML
	private Pane main;

	private Circle circle;

	private Duration duration = new Duration(30000);
	
	@FXML 
	void initialize(){
		circle = new Circle(300, 300, 20);
		circle.setFill(Color.RED);
		
		main.getChildren().add(circle);
		
		circle.setOnMouseClicked(this::handleCircleClick);
	}
	
	private void handleCircleClick(MouseEvent event) {
		duration = duration.divide(2);

		TranslateTransition transition = new TranslateTransition(duration, circle);
		transition.setAutoReverse(true);

		transition.setByX(ThreadLocalRandom.current().nextInt(100));
		transition.setByY(ThreadLocalRandom.current().nextInt(100));
		transition.setCycleCount(Animation.INDEFINITE);

		transition.play();
	}
	
	public void handleKeyPress(KeyEvent event) {
		double x = circle.getCenterX();
		double y = circle.getCenterY();
		
		if(event.getCode() == KeyCode.UP) {
			circle.setCenterY(y - 10);
		} else if(event.getCode() == KeyCode.DOWN) {
			circle.setCenterY(y + 10);
		} else if(event.getCode() == KeyCode.LEFT) {
			circle.setCenterX(x - 10);
		} else if(event.getCode() == KeyCode.RIGHT) {
			circle.setCenterX(x + 10);
		}
	}
}




















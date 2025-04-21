package lab;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.concurrent.ThreadLocalRandom;

public class RandomCircle extends Circle {
    private final TranslateTransition translateToCenter = new TranslateTransition(new Duration(3000), this);
    public boolean captured = false;

    public RandomCircle(double width, double height) {
        super(ThreadLocalRandom.current().nextInt((int) width),
                ThreadLocalRandom.current().nextInt((int) height),
                ThreadLocalRandom.current().nextInt(10, 100),
                new Color[]{Color.GREEN, Color.RED, Color.BLUE}[ThreadLocalRandom.current().nextInt(new Color[]{Color.GREEN, Color.RED, Color.BLUE}.length)]);

        TranslateTransition translateToRandomPoint = new TranslateTransition(new Duration(3000), this);
        translateToRandomPoint.setToX(ThreadLocalRandom.current().nextInt((int) (width - this.getRadius())));
        translateToRandomPoint.setToY(ThreadLocalRandom.current().nextInt((int) (height - this.getRadius())));

        setOnMousePressed(mouseEvent -> {
            captured = true;
            translateToRandomPoint.stop();

            translateToCenter.setToX(width / 2 - this.getCenterX());
            translateToCenter.setToY(height / 2 - this.getCenterY());

            System.out.println("x: " + translateToCenter.getToX());
            System.out.println("y: " + translateToCenter.getToY());

            translateToCenter.play();
        });

        translateToRandomPoint.setAutoReverse(true);
        translateToRandomPoint.setCycleCount(Animation.INDEFINITE);
        translateToRandomPoint.play();
    }
}

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

    public RandomCircle(double width, double height, int radius) {
        super(ThreadLocalRandom.current().nextInt(radius, (int) width - radius),
                ThreadLocalRandom.current().nextInt(radius, (int) height - radius),
                radius,
                new Color[]{Color.GREEN, Color.RED, Color.BLUE}[ThreadLocalRandom.current().nextInt(new Color[]{Color.GREEN, Color.RED, Color.BLUE}.length)]);

        TranslateTransition translateToRandomPoint = new TranslateTransition(new Duration(3000), this);
        translateToRandomPoint.setToX(ThreadLocalRandom.current().nextInt(radius, (int) (width - getCenterX())));
        translateToRandomPoint.setToY(ThreadLocalRandom.current().nextInt(radius, (int) (height - getCenterY())));

        setOnMousePressed(mouseEvent -> {
            captured = true;
            translateToRandomPoint.stop();

            translateToRandomPoint.setFromX(this.getCenterX());
            translateToRandomPoint.setFromY(this.getCenterY());
            translateToCenter.setToX(width / 2 - this.getCenterX());
            translateToCenter.setToY(height / 2 - this.getCenterY());

            translateToCenter.play();
        });

        translateToRandomPoint.setAutoReverse(true);
        translateToRandomPoint.setCycleCount(Animation.INDEFINITE);
        translateToRandomPoint.play();
    }
}

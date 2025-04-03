package lab;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.concurrent.ThreadLocalRandom;

public class RandomCircle extends Circle {
    private final TranslateTransition translateToCenter = new TranslateTransition(new Duration(3000), this);
    private boolean captured = false;

    public RandomCircle(Pane pane) {
        super(ThreadLocalRandom.current().nextInt((int) pane.getWidth()),
                ThreadLocalRandom.current().nextInt((int) pane.getHeight()),
                ThreadLocalRandom.current().nextInt(100),
                new Color[]{Color.GREEN, Color.RED, Color.BLUE}[ThreadLocalRandom.current().nextInt(new Color[]{Color.GREEN, Color.RED, Color.BLUE}.length)]);

        TranslateTransition translateToRandomPoint = new TranslateTransition(new Duration(3000), this);
        translateToRandomPoint.setToX(ThreadLocalRandom.current().nextInt((int) (pane.getWidth() - this.getRadius())));
        translateToRandomPoint.setToY(ThreadLocalRandom.current().nextInt((int) (pane.getHeight() - this.getRadius())));

        setOnMousePressed(mouseEvent -> {
            captured = true;
            translateToRandomPoint.stop();

            translateToCenter.setToX(pane.getWidth() / 2);
            translateToCenter.setToY(pane.getHeight() / 2);

            translateToCenter.play();
        });

        translateToRandomPoint.setAutoReverse(true);
        translateToRandomPoint.setCycleCount(Animation.INDEFINITE);
        translateToRandomPoint.play();
    }
}

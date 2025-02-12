package project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card extends ImageView {
    public static String[] FACES = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "J", "Q", "K"};
    public static int HEIGHT = 130;
    private final String face;

    public Card(String face) {
        this.face = face;
        setImage(new Image(String.format("C:\\Users\\gavinchen\\special-topics\\Java\\Java Labs\\Project 2\\cards\\%s.png",face)));
        setPreserveRatio(true);
        setFitWidth(150);
    }

    public String getFace() {
        return face;
    }

    public int valueOf() {
        return switch (face) {
            case "A" -> 1;
            case "J", "Q", "K" -> 10;
            default -> Integer.parseInt(face);
        };
    }
}

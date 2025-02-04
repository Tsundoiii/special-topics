package project;

public class Card {
    public static String[] FACES = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "J", "Q", "K"};
    public static int HEIGHT = 130;
    private final String face;

    public Card(String face) {
        this.face = face;
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

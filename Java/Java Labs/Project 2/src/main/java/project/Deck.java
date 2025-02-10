package project;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private final ArrayList<Card> cards;
    private final Random random;

    public Deck() {
        cards = new ArrayList<>();
        reset();
        random = new Random();
    }

    public Card dealCard() {
        return cards.remove(random.nextInt(cards.size()));
    }

    public void reset() {
        cards.clear();

        for (String face : Card.FACES) {
            cards.add(new Card(face));
        }
    }
}

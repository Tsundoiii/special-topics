package project;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private final ArrayList<Card> cards;
    private Random random;

    public Deck() {
        cards = new ArrayList<>();
        reset();
    }

    public Card dealCard() {
        return cards.remove(random.nextInt(cards.size()));
    }

    public void reset() {

    }
}

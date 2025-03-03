package project;

import java.util.ArrayList;

public class Player {
    public static Deck deck = new Deck();
    private final ArrayList<Card> hand;
    private int wins;

    public Player() {
        hand = new ArrayList<>();
        wins = 0;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int valueOfHand() {
        int value = hand.stream().mapToInt(Card::valueOf).sum();
        if (value > 21) {
            value = hand.stream().mapToInt(card -> card.getFace().equals("A") ? 1 : card.valueOf()).sum();
        }
        return value;
    }

    public void clearHand() {
        hand.clear();
    }

    public boolean stand(int otherPlayerValue) {
        return valueOfHand() > otherPlayerValue || (valueOfHand() == otherPlayerValue && Math.random() > 0.5);
    }

    public boolean busted() {
        return valueOfHand() > 21;
    }

    public void hit() {
        hand.add(deck.dealCard());
    }

    public int win() {
        return ++wins;
    }
}

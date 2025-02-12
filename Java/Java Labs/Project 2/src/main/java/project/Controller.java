package project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Controller {

    @FXML
    private HBox buttons;

    @FXML
    private Label dealerHandValue;

    @FXML
    private HBox dealerImages;

    @FXML
    private Label dealerWins;

    @FXML
    private Button hit;

    @FXML
    private Button play;

    @FXML
    private Label playerHandValue;

    @FXML
    private HBox playerImages;

    @FXML
    private Label playerWins;

    @FXML
    private Label endtext;

    @FXML
    private Button stand;

    private Player player;
    private Player dealer;

    public void initialize() {
        player = new Player();
        dealer = new Player();

        hit.setVisible(false);
        stand.setVisible(false);
    }

    public void updateHand(Player p, HBox handBox, Label handValue) {
        handBox.getChildren().add(p.getHand().getLast());
        handValue.setText("Value: " + p.valueOfHand());
    }

    @FXML
    void hit() {
        player.hit();
        updateHand(player, playerImages, playerHandValue);

        if (player.valueOfHand() > 21) {
            endGame();
        }
    }

    @FXML
    void stand() {
        while (!dealer.stand(player.valueOfHand()) && dealer.valueOfHand() <= 21) {
            dealer.hit();
            updateHand(dealer, dealerImages, dealerHandValue);
        }

        endGame();
    }

    @FXML
    void startGame() {
        Player.deck.reset();
        player.clearHand();
        dealer.clearHand();

        dealerImages.getChildren().clear();
        playerImages.getChildren().clear();

        playerHandValue.setText("Value: " + player.valueOfHand());
        dealerHandValue.setText("Value: " + dealer.valueOfHand());

        play.setVisible(false);
        hit.setVisible(true);
        stand.setVisible(true);
    }

    void endGame() {
        if (player.valueOfHand() > 21) {
            playerHandValue.setText(("Value: " + player.valueOfHand() + " Bust!"));
            endtext.setText("Dealer wins!");
            dealerWins.setText("Dealer Wins: " + dealer.win());
        } else if (dealer.valueOfHand() > 21) {
            dealerHandValue.setText(("Value: " + dealer.valueOfHand() + " Bust!"));
            endtext.setText("Player wins!");
            playerWins.setText("Player Wins: " + player.win());
        } else if (player.valueOfHand() > dealer.valueOfHand()) {
            endtext.setText("Player wins!");
            playerWins.setText("Player Wins: " + player.win());
        } else if (dealer.valueOfHand() > player.valueOfHand()) {
            endtext.setText("Dealer wins!");
            dealerWins.setText("Dealer Wins: " + dealer.win());
        } else {
            endtext.setText("Push! No one wins.");
        }

        play.setVisible(true);
        hit.setVisible(false);
        stand.setVisible(false);
    }
}


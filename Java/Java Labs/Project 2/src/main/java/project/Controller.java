package project;

import javafx.event.ActionEvent;
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
    void hit(ActionEvent event) {
        player.hit();
        updateHand(player, playerImages, playerHandValue);
    }

    @FXML
    void stand(ActionEvent event) {
        while (dealer.stand(player.valueOfHand())) {
            dealer.hit();
            updateHand(dealer, dealerImages, dealerHandValue);
            System.out.println("stand");
        }
    }

    @FXML
    void startGame(ActionEvent event) {
        Player.deck.reset();
        player.clearHand();
        dealer.clearHand();

        play.setVisible(false);
        hit.setVisible(true);
        stand.setVisible(true);
    }

}


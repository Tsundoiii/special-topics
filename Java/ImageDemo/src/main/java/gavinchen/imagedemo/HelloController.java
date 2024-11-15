package gavinchen.imagedemo;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class HelloController {
    @FXML
    private Pane root;

    private ArrayList<Image> images = new ArrayList<>();
    private int index = 0;

    @FXML
    void initialize() {
        VBox main = new VBox();
        main.setAlignment(Pos.CENTER);
        root.getChildren().add(main);

        Button button = new Button("Next");

        ImageView imageView = new ImageView();
        imageView.setFitHeight(600);
        imageView.setFitWidth(600);
        imageView.setPreserveRatio(true);

        for (int i = 1; i <=7; i++) {
            images.add(new Image(getClass().getResourceAsStream(i + ".jpg")));
        }

        imageView.setImage(images.get(index));

        button.setOnAction(actionEvent -> imageView.setImage(images.get(++index % 7)));

        main.getChildren().addAll(button, imageView);
    }
}
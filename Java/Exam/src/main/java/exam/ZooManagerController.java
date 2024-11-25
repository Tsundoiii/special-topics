package exam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class ZooManagerController {

    @FXML
    private ImageView animalIV;

    //Exhibit VBox's
    @FXML
    private VBox exhibitPlains;
    @FXML
    private VBox exhibitForest;
    @FXML
    private VBox exhibitMountains;

    //Exhibit Radio Buttons
    @FXML
    private RadioButton plainsRdo;
    @FXML
    private RadioButton forestRdo;
    @FXML
    private RadioButton mountainsRdo;

    //Contains all animal pictures
    private ArrayList<Image> images;

    //Initialize method is already completed
    @FXML
    void initialize() {
        //Setup for all animal images
        images = new ArrayList<Image>();
        images.add(new Image(getClass().getResourceAsStream("giraffe.jpg")));
        images.add(new Image(getClass().getResourceAsStream("hippo.jpeg")));
        images.add(new Image(getClass().getResourceAsStream("lemur.jpg")));
        images.add(new Image(getClass().getResourceAsStream("leopard.jpg")));
        images.add(new Image(getClass().getResourceAsStream("llama.jpg")));
        images.add(new Image(getClass().getResourceAsStream("monkey.jpg")));
        images.add(new Image(getClass().getResourceAsStream("okapi.jpg")));
        images.add(new Image(getClass().getResourceAsStream("panda.jpg")));

        //Set the first animal image
        animalIV.setImage(images.remove(0));

        //Apply clipping to hide overflow
        Rectangle clip1 = new Rectangle(exhibitPlains.getPrefWidth(), exhibitPlains.getPrefHeight());
        exhibitPlains.setClip(clip1);
        Rectangle clip2 = new Rectangle(exhibitForest.getPrefWidth(), exhibitForest.getPrefHeight());
        exhibitPlains.setClip(clip2);
        Rectangle clip3 = new Rectangle(exhibitMountains.getPrefWidth(), exhibitMountains.getPrefHeight());
        exhibitPlains.setClip(clip3);
    }

    @FXML
    void addAnimal(ActionEvent event) {
        /*
         * A reference to the exhibit
         *  that should be added to
         */
        VBox exhibitToAddTo = null;

        //Determine and assign the exhibit to add to
        if (plainsRdo.isSelected()) {
            exhibitToAddTo = exhibitPlains;
        } else if (forestRdo.isSelected()) {
            exhibitToAddTo = exhibitForest;
        } else if (mountainsRdo.isSelected()) {
            exhibitToAddTo = exhibitMountains;
        }

        /*******************************************
         * Do not change any code above
         */


        //TODO
        //Make box to hold the image and remove button that will be
        //added to exhibits. Call the box animalBox.
        VBox animalBox = new VBox();


        //TODO
        //Give the new box center alignment
        animalBox.setAlignment(Pos.CENTER);


        //Creates a new image control and gets the image displayed in
        //the ImageView at the bottom of the app.
        ImageView newAnimal = new ImageView(animalIV.getImage());


        //TODO
        //Set fit height and width to 85 for the new image
        newAnimal.setFitHeight(85);
        newAnimal.setFitWidth(85);


        //TODO
        //Preserve the aspect ratio for the new image control
        newAnimal.setPreserveRatio(true);


        //TODO
        //Create a button for removing the animal box
        Button remove = new Button("Remove");


        //TODO
        //Set the new button action event and use only
        //the following code inside your lambda expression:


        //		VBox containingExhibit = (VBox)animalBox.getParent();
        //		containingExhibit.getChildren().remove(animalBox);
        remove.setOnAction(actionEvent -> {
            VBox containingExhibit = (VBox) animalBox.getParent();
            containingExhibit.getChildren().remove(animalBox);
        });


        //TODO
        //Put the image and button in the box you created.
        animalBox.getChildren().addAll(newAnimal, remove);


        //TODO
        //Put the box you created in the desired exhibit.
        //The code that determined the user's desired exhibit
        //was completed for you in lines 71-82.
        exhibitToAddTo.getChildren().add(animalBox);


        /*******************************************
         * Do not change any code below
         */
        //Get the next animal
        if (!images.isEmpty()) {
            animalIV.setImage(images.remove(0));
        } else {
            animalIV.setImage(null);
        }
    }


}


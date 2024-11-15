package exam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ContactController {

	@FXML
	private VBox contactsBox;

	@FXML
	private TextField email;

	@FXML
	private TextField name;

	@FXML
	void addContact(ActionEvent event) {
		String contactName = name.getText();
		String contactEmail = email.getText();

		HBox contact = new HBox();
		contact.setAlignment(Pos.CENTER);
		contact.setSpacing(10);
		Button delete = new Button();
		delete.setText("Delete");
		delete.setOnAction(actionEvent -> contactsBox.getChildren().remove(contact));

		contact.getChildren().addAll(new Label(contactName), new Label(contactEmail), delete);

		contactsBox.getChildren().add(contact);
	}

}
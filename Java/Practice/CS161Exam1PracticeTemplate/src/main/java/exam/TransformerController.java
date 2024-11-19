package exam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TransformerController {

	@FXML
	private TextField originalTxt;

	@FXML
	private TextField replaceTxt;

	@FXML
	private Label resultLbl;

	@FXML
	void initialize() {
		originalTxt.setText("Hello World!");
		replaceTxt.setText("o,u");
	}

	@FXML
	void transform(ActionEvent event) {
		String original = originalTxt.getText();
		String[] replace = replaceTxt.getText().split(",");
		String old = replace[0];
		String replacement = replace[1];

		StringBuilder newString = new StringBuilder(original);

		int totalLength = original.length();

		for (int i = 0; i < original.length() - old.length(); i++) {
			if (original.substring(i, i + old.length()).equals(old)) {
				newString.replace(i, i + old.length(), replacement);
			}
		}

		resultLbl.setText(newString.toString());
	}

}
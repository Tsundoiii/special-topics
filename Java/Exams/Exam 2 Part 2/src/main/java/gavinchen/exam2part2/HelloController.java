package gavinchen.exam2part2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class HelloController {

    private final ObservableList<Integer> numbers = FXCollections.observableArrayList();
    @FXML
    private Button analyze;
    @FXML
    private Label averageOfNumbers;
    @FXML
    private Label largestSmallest;
    @FXML
    private MenuItem openFile;
    @FXML
    private Label totalEvenNumbers;
    @FXML
    private ListView<Integer> numbersView;

    public void initialize() {
        numbersView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        openFile.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("."));
            File pricesFile = fileChooser.showOpenDialog(new Stage());

            try (FileInputStream fileInputStream = new FileInputStream(pricesFile)) {
                try (DataInputStream dataInputStream = new DataInputStream((fileInputStream))) {
                    while (dataInputStream.available() > 0) {
                        numbers.add(dataInputStream.readInt());
                    }
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Not a number");
                    alert.setContentText("We found a non-numeric value in your binary file");
                    alert.showAndWait();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            numbersView.setItems(numbers);
        });

        analyze.setOnAction(event -> {
            ObservableList<Integer> selected = numbersView.getSelectionModel().getSelectedItems();

            double evens = selected.stream().filter(i -> i % 2 == 0).count();
            double average = selected.stream().mapToInt(i -> i).average().getAsDouble();
            double range = selected.stream().mapToInt(i -> i).max().getAsInt() - selected.stream().mapToInt(i -> i).min().getAsInt();

            totalEvenNumbers.setText("Total even #s: " + evens);
            averageOfNumbers.setText("Average of #s: " + String.format("%.2f", average));
            largestSmallest.setText("Largest - Smallest: " + range);
        });
    }
}

package com.example.exceptionsiojavafxquiz;

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

    private final ObservableList<Double> prices = FXCollections.observableArrayList();
    private double totalValue = 0;
    @FXML
    private Button calculateChange;
    @FXML
    private TextField cashReceived;
    @FXML
    private Label change;
    @FXML
    private Button getTotal;
    @FXML
    private MenuItem loadPrices;
    @FXML
    private ListView<Double> pricesView;
    @FXML
    private Label total;

    public void initialize() {
        pricesView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        loadPrices.setOnAction(event -> {
            System.out.println("load");
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("."));
            File pricesFile = fileChooser.showOpenDialog(new Stage());

            try (FileInputStream fileInputStream = new FileInputStream(pricesFile)) {
                try (DataInputStream dataInputStream = new DataInputStream((fileInputStream))) {
                    while (dataInputStream.available() > 0) {
                        prices.add(dataInputStream.readDouble());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            pricesView.setItems(prices);
        });

        getTotal.setOnAction(event -> {
            ObservableList<Double> selected = pricesView.getSelectionModel().getSelectedItems();
            totalValue = selected.stream().mapToDouble(x -> x).sum();
            total.setText(String.format("Total: $%.2f", totalValue));
        });

        calculateChange.setOnAction(event -> {
            double cashReceivedValue = 0;
            try {
                cashReceivedValue = Double.parseDouble(cashReceived.getText());
            } catch (NumberFormatException nfe) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Not a number");
                alert.setContentText("Make sure you only enter numeric values.");
                alert.showAndWait();
            }

            change.setText(String.format("Change: $%.2f", cashReceivedValue - totalValue));
        });
    }
}

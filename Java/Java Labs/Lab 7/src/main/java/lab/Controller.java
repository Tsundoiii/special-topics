package lab;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    private final ArrayList<Book> books = new ArrayList<>();
    private final ArrayList<Book> cartBooks = new ArrayList<>();
    private final ObservableList<String> titles = FXCollections.observableArrayList();
    private final ObservableList<String> cartTitles = FXCollections.observableArrayList();

    @FXML
    private GridPane gridPane;
    @FXML
    private MenuItem loadBooks;
    @FXML
    private MenuItem light;
    @FXML
    private MenuItem dark;
    @FXML
    private MenuItem idk;
    @FXML
    private MenuItem exit;
    @FXML
    private MenuItem clearCart;
    @FXML
    private MenuItem checkOut;
    @FXML
    private ListView<String> availableBooks;
    @FXML
    private ListView<String> shoppingCart;
    @FXML
    private Button addToCart;
    @FXML
    private Button removeFromCart;

    public void initialize() {
        availableBooks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        loadBooks.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("."));
            File booksFile = fileChooser.showOpenDialog(new Stage());

            try (FileInputStream fileInputStream = new FileInputStream(booksFile)) {
                try (DataInputStream dataInputStream = new DataInputStream((fileInputStream))) {
                    while (dataInputStream.available() > 0) {
                        String title = dataInputStream.readUTF();
                        double price = dataInputStream.readDouble();
                        Book book = new Book(title, price);

                        books.add(book);
                        titles.add(book.title());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            availableBooks.setItems(titles);
        });

        light.setOnAction(event -> {
            for (int i = availableBooks.getStyleClass().size() - 1; i > 0; i--) {
                availableBooks.getStyleClass().remove(i);
                shoppingCart.getStyleClass().remove(i);

                addToCart.getStyleClass().remove(i);
                removeFromCart.getStyleClass().remove(i);
                gridPane.getStyleClass().remove(i);
            }

        });

        dark.setOnAction(event -> {
            availableBooks.getStyleClass().add("list-view-dark");
            shoppingCart.getStyleClass().add("list-view-dark");

            addToCart.getStyleClass().add("button-dark");
            removeFromCart.getStyleClass().add("button-dark");
            gridPane.getStyleClass().add("root-dark");
        });

        idk.setOnAction(event -> {
            availableBooks.getStyleClass().add("list-view-idk");
            shoppingCart.getStyleClass().add("list-view-idk");

            addToCart.getStyleClass().add("button-idk");
            removeFromCart.getStyleClass().add("button-idk");
            gridPane.getStyleClass().add("root-idk");
        });

        exit.setOnAction(event -> System.exit(0));

        clearCart.setOnAction(event -> {
            cartBooks.clear();
            cartTitles.clear();
        });

        checkOut.setOnAction(event -> {
            double subtotal = 0;

            for (String title : cartTitles) {
                for (Book book : books) {
                    if (title.equals(book.title())) {
                        subtotal += book.price();
                    }
                }
            }

            double tax = subtotal * 0.07;

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Checkout");
            alert.setHeaderText("Checkout Details");
            alert.setContentText(String.format(
                    "%-15s$%-8.2f\n%-15s$%-8.2f\n%-15s$%-8.2f",
                    "Subtotal:", subtotal,
                    "Tax:", tax,
                    "Total:", subtotal + tax));
            alert.showAndWait();
        });

        addToCart.setOnAction(event -> {
            availableBooks.getSelectionModel().getSelectedItems();
        });

        addToCart.setOnAction(event -> {
            ObservableList<String> selected = availableBooks.getSelectionModel().getSelectedItems();
            cartTitles.addAll(selected);
            shoppingCart.setItems(cartTitles);
        });

        removeFromCart.setOnAction(event -> {
            ObservableList<Integer> selected = shoppingCart.getSelectionModel().getSelectedIndices();
            for (int i : selected) {
                cartTitles.remove(i);
            }
            shoppingCart.setItems(cartTitles);
        });
    }
}

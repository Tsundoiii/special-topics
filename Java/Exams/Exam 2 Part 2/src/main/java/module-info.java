module gavinchen.exam2part2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens gavinchen.exam2part2 to javafx.fxml;
    exports gavinchen.exam2part2;
}
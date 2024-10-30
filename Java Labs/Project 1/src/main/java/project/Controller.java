package project;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Controller {
    private final HashMap<Integer, Double> interestRates = new HashMap<>();
    @FXML
    private TextField basePrice;
    @FXML
    private TextField downPayment;
    @FXML
    private RadioButton fortyEightMonths;
    @FXML
    private Text monthlyPayment;
    @FXML
    private CheckBox rearCamera;
    @FXML
    private TextField salesTax;
    private final TextField[] financingInformation = {basePrice, downPayment, salesTax};
    @FXML
    private RadioButton sixtyMonths;
    @FXML
    private CheckBox sunRoof;
    @FXML
    private RadioButton thirtySixMonths;
    @FXML
    private Text totalLoanAmount;
    @FXML
    private Text totalPayment;
    private final Text[] paymentInformation = {totalLoanAmount, monthlyPayment, totalPayment};
    @FXML
    private CheckBox touchScreen;
    private final Stream<CheckBox> extraOptions = Stream.of(sunRoof, touchScreen, rearCamera);
    private final Supplier<Stream<CheckBox>> extraOptionsSupplier = () -> Stream.of(sunRoof, touchScreen, rearCamera);
    @FXML
    private RadioButton twentyFourMonths;
    private final RadioButton[] loanTerm = {twentyFourMonths, thirtySixMonths, fortyEightMonths, sixtyMonths};

    public void initialize() {
        interestRates.put(24, 0.07);
        interestRates.put(36, 0.06);
        interestRates.put(48, 0.055);
        interestRates.put(60, 0.05);
    }

    private double totalLoan(double basePrice, double optionCosts, double downPayment, double taxRate) {
        double subtotal = basePrice + optionCosts;
        double tax = subtotal * taxRate;

        return subtotal + tax - downPayment;
    }

    private double monthlyPayment(double annualInterestRate, double months) {
        double monthlyInterest = annualInterestRate / 12;
        double monthScale = Math.pow(1 + monthlyInterest, months);

        return totalLoan(Double.parseDouble(basePrice.getText()), extraOptionsSupplier.get().mapToDouble(option -> Double.parseDouble(option.getText().substring(option.getText().indexOf("$") + 1))).sum(), Double.parseDouble(downPayment.getText()), Double.parseDouble(salesTax.getText()) / 10) * monthlyInterest * monthScale / (monthScale - 1);
    }

    private int loanTerm() {
        if (twentyFourMonths.isSelected()) {
            return Integer.parseInt(twentyFourMonths.getText().substring(0, 2));
        }

        if (thirtySixMonths.isSelected()) {
            return Integer.parseInt(thirtySixMonths.getText().substring(0, 2));
        }

        if (fortyEightMonths.isSelected()) {
            return Integer.parseInt(fortyEightMonths.getText().substring(0, 2));
        }

        if (sixtyMonths.isSelected()) {
            return Integer.parseInt(sixtyMonths.getText().substring(0, 2));
        }

        return -1;
    }

    private double annualInterestRate() {
        return interestRates.get(loanTerm());
    }

    private double totalPayment() {
        return monthlyPayment(annualInterestRate(), loanTerm()) * loanTerm();
    }

    private String priceFormat(double price) {
        return String.format(".2f", price);
    }

    @FXML
    private void calculate() {
        totalLoanAmount.setText(String.format(Double.toString(totalLoan(Double.parseDouble(basePrice.getText()), extraOptionsSupplier.get().mapToDouble(option -> Double.parseDouble(option.getText().substring(option.getText().indexOf("$") + 1))).sum(), Double.parseDouble(downPayment.getText()), Double.parseDouble(salesTax.getText()) / 10)), "%.2f"));
        monthlyPayment.setText(String.format(Double.toString(monthlyPayment(annualInterestRate(), loanTerm())), "%.2f"));
        totalPayment.setText(String.format(Double.toString(totalPayment()), "%.2f"));
    }

    @FXML
    private void reset() {
        for (Text amount : paymentInformation) {
            amount.setText("0.0");
        }

        for (TextField field : financingInformation) {
            field.clear();
        }

        for (RadioButton term : loanTerm) {
            term.setSelected(false);
        }

        extraOptions.forEach(option -> option.setSelected(false));
    }

}

package project;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.stream.Stream;

public class Controller {
    @FXML
    private TextField basePrice;
    @FXML
    private TextField downPayment;
    @FXML
    private RadioButton fourtyEightMonths;
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
    @FXML
    private RadioButton twentyFourMonths;
    private final RadioButton[] loanTerm = {twentyFourMonths, thirtySixMonths, fourtyEightMonths, sixtyMonths};
    private HashMap<Integer, Double> interestRates;

    public void initialize() {
        interestRates.put(24, 0.07);
        interestRates.put(36, 0.06);
        interestRates.put(48, 0.055);
        interestRates.put(60, 0.05);
    }

    private double totalLoan(double basePrice, double optionCosts, double downPayment, double taxRate) {
        double subtotal = basePrice + optionCosts;
        double tax = subtotal + taxRate;

        return subtotal * tax - downPayment;
    }

    private double monthlyPayment(double annualInterestRate, double months) {
        double monthlyInterest = annualInterestRate / 12;
        double monthScale = Math.pow(1 + monthlyInterest, months);

        return totalLoan(Double.parseDouble(basePrice.getText()), extraOptions.mapToDouble(option -> Double.parseDouble(option.getText())).sum(), Double.parseDouble(downPayment.getText()), Double.parseDouble(salesTax.getText())) * monthlyInterest * monthScale / (monthScale - 1);
    }

    private int loanTerm() {
        for (RadioButton term : loanTerm) {
            if (term.isSelected()) {
                return Integer.parseInt(term.getText().substring(0, 2));
            }
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
        totalLoanAmount.setText(Double.toString((totalLoan(Double.parseDouble(basePrice.getText()), extraOptions.mapToDouble(option -> Double.parseDouble(option.getText())).sum(), Double.parseDouble(downPayment.getText()), Double.parseDouble(salesTax.getText())))));
        monthlyPayment.setText(Double.toString(monthlyPayment(annualInterestRate(), loanTerm())));
        totalPayment.setText(Double.toString(totalPayment()));
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

package lab;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Name: Gavin Chen
 * Username: gavinchen
 */

public class Controller {
    @FXML
    private Button calculate;

    @FXML
    private TextField cmT;

    @FXML
    private Button clear;

    @FXML
    private Button exit;

    @FXML
    private TextField inT;

    @FXML
    private TextField mT;

    @FXML
    private TextField yT;
    private final TextField[] textFields = {inT, yT, cmT, mT};
    @FXML
    private Label errorL;

    public static double cm2in(double cm) {
        return cm * 0.393701;
    }

    public static double cm2m(double cm) {
        return cm / 100;
    }

    public static double cm2y(double cm) {
        return cm2in(cm) / 36;
    }

    public static double in2cm(double in) {
        return in * 2.54;
    }

    public static double in2y(double in) {
        return in / 36;
    }

    public static double in2m(double in) {
        return in2cm(in) / 100;
    }

    public static double y2m(double y) {
        return y * 0.9144;
    }

    public static double y2in(double y) {
        return y / 36;
    }

    public static double y2cm(double y) {
        return y2m(y) * 100;
    }

    public static double m2y(double m) {
        return m * 1.09361;
    }

    public static double m2in(double m) {
        return m2y(m) * 36;
    }

    public static double m2cm(double m) {
        return m * 100;
    }

    public void convert() {
        int count = 0;
        if (!inT.getText().isEmpty()) {
            count++;
        }
        if (!yT.getText().isEmpty()) {
            count++;
        }
        if (!cmT.getText().isEmpty()) {
            count++;
        }
        if (!mT.getText().isEmpty()) {
            count++;
        }

        if (count > 1) {
            errorL.setVisible(true);
            return;
        }

        if (!inT.getText().isEmpty()) {
            inConvert();
        } else if (!yT.getText().isEmpty()) {
            yConvert();
        } else if (!cmT.getText().isEmpty()) {
            cmConvert();
        } else if (!mT.getText().isEmpty()) {
            mConvert();
        }
    }

    public void cmConvert() {
        inT.setText(String.format("%.2f", cm2in(Double.parseDouble(cmT.getText()))));
        yT.setText(String.format("%.2f", cm2y(Double.parseDouble(cmT.getText()))));
        mT.setText(String.format("%.2f", cm2m(Double.parseDouble(cmT.getText()))));
    }

    public void inConvert() {
        yT.setText(String.format("%.2f", in2y(Double.parseDouble(inT.getText()))));
        cmT.setText(String.format("%.2f", in2cm(Double.parseDouble(inT.getText()))));
        mT.setText(String.format("%.2f", in2m(Double.parseDouble(inT.getText()))));
    }

    public void yConvert() {
        inT.setText(String.format("%.2f", y2in(Double.parseDouble(yT.getText()))));
        cmT.setText(String.format("%.2f", y2cm(Double.parseDouble(yT.getText()))));
        mT.setText(String.format("%.2f", y2m(Double.parseDouble(yT.getText()))));

    }

    public void mConvert() {
        inT.setText(String.format("%.2f", m2in(Double.parseDouble(mT.getText()))));
        yT.setText(String.format("%.2f", m2y(Double.parseDouble(mT.getText()))));
        cmT.setText(String.format("%.2f", m2cm(Double.parseDouble(mT.getText()))));
    }

    public void clear() {
        inT.setText("");
        yT.setText("");
        cmT.setText("");
        mT.setText("");

        errorL.setVisible(false);
    }

    public void exit() {
        System.exit(0);
    }
}

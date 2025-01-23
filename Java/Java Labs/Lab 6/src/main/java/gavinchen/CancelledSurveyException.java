package gavinchen;

public class CancelledSurveyException extends Exception {
    public CancelledSurveyException() {
        super("Your survey was cancelled.");
    }

    public CancelledSurveyException(String message) {
        super(message + "\nYour survey was cancelled.");
    }
}

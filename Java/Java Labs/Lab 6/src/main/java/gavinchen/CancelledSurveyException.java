package gavinchen;

public class CancelledSurveyException extends Exception {
    public CancelledSurveyException(String message) {
        super(message + "\nYour survey was cancelled.");
    }
}

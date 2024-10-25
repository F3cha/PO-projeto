package hva.app.exception;

public class WrongEmployeeTypeException extends RuntimeException {
    public WrongEmployeeTypeException(String message) {
        super(message);
    }
}

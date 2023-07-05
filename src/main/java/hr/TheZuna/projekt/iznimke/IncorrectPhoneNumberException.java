package hr.TheZuna.projekt.iznimke;

public class IncorrectPhoneNumberException extends RuntimeException{

    public IncorrectPhoneNumberException(String message) {
        super(message);
    }

    public IncorrectPhoneNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectPhoneNumberException(Throwable cause) {
        super(cause);
    }
}

package hr.TheZuna.projekt.iznimke;

public class NotAnEmailExeption extends Exception{
    public NotAnEmailExeption(String message) {
        super(message);
    }

    public NotAnEmailExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAnEmailExeption(Throwable cause) {
        super(cause);
    }
}

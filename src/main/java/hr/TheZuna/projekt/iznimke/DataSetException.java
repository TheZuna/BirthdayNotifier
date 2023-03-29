package hr.TheZuna.projekt.iznimke;

public class DataSetException extends Exception{

    public DataSetException(String message) {
        super(message);
    }

    public DataSetException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataSetException(Throwable cause) {
        super(cause);
    }
}

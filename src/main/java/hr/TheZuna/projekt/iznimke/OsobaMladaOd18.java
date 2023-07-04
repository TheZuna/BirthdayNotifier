package hr.TheZuna.projekt.iznimke;

public class OsobaMladaOd18 extends RuntimeException{
    public OsobaMladaOd18(String message) {
        super(message);
    }

    public OsobaMladaOd18(String message, Throwable cause) {
        super(message, cause);
    }

    public OsobaMladaOd18(Throwable cause) {
        super(cause);
    }
}

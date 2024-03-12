package helpers;

public class SalidaInvalidaException extends Exception {
	public SalidaInvalidaException() {}

    public SalidaInvalidaException(String message) {
       super(message);
    }
}
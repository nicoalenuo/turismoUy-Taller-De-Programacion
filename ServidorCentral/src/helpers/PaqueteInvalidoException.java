package helpers;

public class PaqueteInvalidoException extends Exception {
	public PaqueteInvalidoException() {}

    public PaqueteInvalidoException(String message) {
       super(message);
    }
}

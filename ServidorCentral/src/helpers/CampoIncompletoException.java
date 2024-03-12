package helpers;

public class CampoIncompletoException extends Exception {
	public CampoIncompletoException() {}

    public CampoIncompletoException(String message) {
       super(message);
    }
}

package helpers;

public class CostoEsCeroException extends Exception {
	public CostoEsCeroException() {}

    public CostoEsCeroException(String message) {
       super(message);
    }
}

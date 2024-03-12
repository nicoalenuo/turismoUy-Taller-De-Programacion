package helpers;

public class CategoriaInvalidaException extends Exception {
	public CategoriaInvalidaException() {}

    public CategoriaInvalidaException(String message) {
       super(message);
    }
}

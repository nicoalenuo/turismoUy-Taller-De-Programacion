package helpers;

public class UsuarioInexistenteException extends Exception {
	public UsuarioInexistenteException() {}

    public UsuarioInexistenteException(String message) {
       super(message);
    }
}

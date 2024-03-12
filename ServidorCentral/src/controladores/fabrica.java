package controladores;

public class fabrica {
	private static fabrica instancia = null;
	
	private fabrica() {
		
	}
	
	public static fabrica getInstance() {
		if (instancia == null)
			instancia = new fabrica();
		return instancia;
	}
	
	public IControladorTurismo getIControladorTurismo() {
		return new ControladorTurismo();
	}
	
	public IControladorUsuario getIControladorUsuarios() {
		return new ControladorUsuario();
	}
	
	public IControladorPaquete getIControladorPaquete() {
		return new ControladorPaquete();
	}
	
}

package interfaces;

import servidorActividades.WSActividades;
import servidorPaquetes.WSPaquetes;
import servidorSalidas.WSSalidas;
import servidorUsuarios.WSUsuarios;

public class turismoUy {
	public static void main(String[] args) {
		WSUsuarios wsu = new WSUsuarios();
		WSActividades wsa = new WSActividades();
		WSSalidas wss = new WSSalidas();
		WSPaquetes wsp = new WSPaquetes();
		wsu.publicar();
		wsa.publicar();
		wss.publicar();
		wsp.publicar();
		
		new mainFrame(); 
	}
} //

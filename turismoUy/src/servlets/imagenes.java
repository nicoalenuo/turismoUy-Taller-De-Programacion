package servlets;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidoractividades.WSActividades;
import servidoractividades.WSActividadesService;
import servidorpaquetes.WSPaquetes;
import servidorpaquetes.WSPaquetesService;
import servidorsalidas.WSSalidas;
import servidorsalidas.WSSalidasService;
import servidorusuarios.WSUsuarios;
import servidorusuarios.WSUsuariosService;

@WebServlet("/imagenes")
public class imagenes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public imagenes() {
        super();
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String id = (String) request.getParameter("id");
        
        byte[] img = null;
        String tipo = request.getParameter("tipo");
       
        
        if (tipo.equals("usuario")) {
	        try {
	        	WSUsuariosService wsu = new WSUsuariosService();
	            WSUsuarios port = wsu.getWSUsuariosPort();
	            img = port.getFile(id);	            
	        } catch (Exception ex) {         
	        	ex.printStackTrace();
	        }
        }
        else if (tipo.equals("actividad")) {
	        try {
	        	WSActividadesService wsa = new WSActividadesService();
	            WSActividades port = wsa.getWSActividadesPort();
	            img = port.getFile(id);	            
	        } catch (Exception ex) {         
	        	ex.printStackTrace();
	        }
        }else if (tipo.equals("salida")) {
	        try {
	        	WSSalidasService wss = new WSSalidasService();
	            WSSalidas port = wss.getWSSalidasPort();
	            img = port.getFile(id);	            
	        } catch (Exception ex) {         
	        	ex.printStackTrace();
	        }
        }
        else{
	        try {
	        	WSPaquetesService wsp = new WSPaquetesService();
	            WSPaquetes port = wsp.getWSPaquetesPort();
	            img = port.getFile(id);	            
	        } catch (Exception ex) {         
	        	ex.printStackTrace();
	        }
        }
              
        response.setContentType("image/jpeg");
        response.setContentLength((int) img.length);
        OutputStream out = response.getOutputStream();
        out.write(img);
        out.close();
	}

}

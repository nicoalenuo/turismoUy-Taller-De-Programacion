package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidoractividades.ActividadEnPaqueteException_Exception;
import servidoractividades.TieneSalidaVigenteException_Exception;
import servidoractividades.WSActividades;
import servidoractividades.WSActividadesService;
import servidorusuarios.DtUsuario;
import servidorusuarios.WSUsuarios;
import servidorusuarios.WSUsuariosService;

@WebServlet("/finalizarActividad")
public class finalizarActividad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public finalizarActividad() {
        super();
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	WSActividadesService wsu = new WSActividadesService();
        WSActividades port = wsu.getWSActividadesPort();
    	
        String nick = ((DtUsuario) request.getSession().getAttribute("datosUsuarioSesion")).getNickname();
    	String act = (String) request.getParameter("actividadCancelar");
    	
    	try {
			port.finalizarActividad(act);
			System.out.println("DD");
			response.sendRedirect("consultaUsuario?id="+nick);
		} catch (ActividadEnPaqueteException_Exception e) {
			response.sendRedirect("consultaUsuario?id="+nick+"&error=actEnPaquete");
		} catch (TieneSalidaVigenteException_Exception e) {
			response.sendRedirect("consultaUsuario?id="+nick+"&error=salidaVigente");
		}	
	}
}

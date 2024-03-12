package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidoractividades.StringDTCategoriaMap;
import servidoractividades.StringDTDepartamentoMap;
import servidoractividades.WSActividades;
import servidoractividades.WSActividadesService;

@WebServlet("/home")
public class home extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public home() {
        super();
    }
    
    
	private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getSession().getAttribute("datosUsuarioSesion") == null) {
			WSActividadesService wsa = new WSActividadesService();
            WSActividades port2 = wsa.getWSActividadesPort(); 	
            List<StringDTCategoriaMap.Entry> catsMenu = port2.obtenerCategorias().getEntry();
        	List<StringDTDepartamentoMap.Entry> depsMenu = port2.obtenerDepartamentos().getEntry();
        	req.setAttribute("depsMenu", depsMenu);
        	req.setAttribute("catsMenu", catsMenu);
			
			req.getRequestDispatcher("/WEB-INF/JSPs/inicioSesion.jsp").forward(req, resp);
		}
		else {
			resp.sendRedirect("index");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}


}

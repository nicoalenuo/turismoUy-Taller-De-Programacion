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
import servidorpaquetes.WSPaquetes;
import servidorpaquetes.WSPaquetesService;


@WebServlet("/consultaPaquete")
public class consultaPaquete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public consultaPaquete() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	if (req.getHeader("User-Agent").contains("Mobi")) {
    		resp.sendRedirect("home");
    	} else {
    		WSPaquetesService wsp = new WSPaquetesService();
            WSPaquetes port = wsp.getWSPaquetesPort();
            
        	req.setCharacterEncoding("UTF-8");
        	
        	WSActividadesService wsa = new WSActividadesService();
            WSActividades port2 = wsa.getWSActividadesPort(); 	
            List<StringDTCategoriaMap.Entry> catsMenu = port2.obtenerCategorias().getEntry();
        	List<StringDTDepartamentoMap.Entry> depsMenu = port2.obtenerDepartamentos().getEntry();
        	req.setAttribute("depsMenu", depsMenu);
        	req.setAttribute("catsMenu", catsMenu);
        	
        	String idPaq = req.getParameter("id");
        	
        	if (idPaq==null) { 	
    			req.setAttribute("paquetes", port.obtenerDatosPaquetes());
    			req.getRequestDispatcher("/WEB-INF/JSPs/listadoPaquete.jsp").forward(req, resp);
        	} else {
        		req.setAttribute("dtpaq", port.seleccionarPaquete(idPaq));
        		req.setAttribute("acts", port.actsEnPaq(idPaq));
        		req.setAttribute("categorias", port.categoriasEnPaq(idPaq));
        		req.getRequestDispatcher("/WEB-INF/JSPs/consultaPaquete.jsp").forward(req, resp);
        	}
    	}  	
	}
}
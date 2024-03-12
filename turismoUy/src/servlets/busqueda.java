package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidoractividades.StringDTActividadMap;
import servidoractividades.StringDTCategoriaMap;
import servidoractividades.StringDTDepartamentoMap;
import servidoractividades.WSActividades;
import servidoractividades.WSActividadesService;
import servidorpaquetes.StringDTPaqueteMap;
import servidorpaquetes.WSPaquetes;
import servidorpaquetes.WSPaquetesService;

@WebServlet("/busqueda")
public class busqueda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public busqueda() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getHeader("User-Agent").contains("Mobi")) {
    		response.sendRedirect("home");
    	} else {
    		WSActividadesService wsa = new WSActividadesService();
            WSActividades port = wsa.getWSActividadesPort();	
            List<StringDTCategoriaMap.Entry> catsMenu = port.obtenerCategorias().getEntry();
        	List<StringDTDepartamentoMap.Entry> depsMenu = port.obtenerDepartamentos().getEntry();
        	request.setAttribute("depsMenu", depsMenu);
        	request.setAttribute("catsMenu", catsMenu);
            
            WSPaquetesService wsp = new WSPaquetesService();
            WSPaquetes port2 = wsp.getWSPaquetesPort();
    	
            StringDTDepartamentoMap deps = port.obtenerDepartamentos();
            StringDTCategoriaMap cats = port.obtenerCategorias();
    		
            request.setAttribute("departamentos", deps);
            request.setAttribute("categorias", cats);
            
    		String q = request.getParameter("q");
    		String depto = request.getParameter("departamento");
    		String cat = request.getParameter("categoria");
    		
    		if(depto==null)
    			depto = "Todos los departamentos";
    		if (cat==null)
    			cat = "Todas las categorias";
    		
    		StringDTActividadMap acts = port.busqueda(q, depto, cat);
    		request.setAttribute("actividades", acts);
    		
    		StringDTPaqueteMap paqs = port2.busqueda(q, depto, cat);
    		request.setAttribute("paquetes", paqs);
    		
    		request.getRequestDispatcher("/WEB-INF/JSPs/busqueda.jsp").forward(request, response);
    	}	
	}
}

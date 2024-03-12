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

@WebServlet("/index")
public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public index() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	WSActividadesService wsu = new WSActividadesService();
        WSActividades port = wsu.getWSActividadesPort(); 	
    	List<StringDTCategoriaMap.Entry> catsMenu = port.obtenerCategorias().getEntry();
    	List<StringDTDepartamentoMap.Entry> depsMenu = port.obtenerDepartamentos().getEntry();
    	
    	request.setAttribute("depsMenu", depsMenu);
    	request.setAttribute("catsMenu", catsMenu);
   	
    	request.getRequestDispatcher("/WEB-INF/JSPs/index.jsp").forward(request, response);
	}
}

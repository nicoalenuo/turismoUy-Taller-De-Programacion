package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servidoractividades.StringDTCategoriaMap;
import servidoractividades.StringDTDepartamentoMap;
import servidoractividades.WSActividades;
import servidoractividades.WSActividadesService;
import servidorusuarios.DtProveedor;
import servidorusuarios.DtUsuario;
import servidorusuarios.WSUsuarios;
import servidorusuarios.WSUsuariosService;


@WebServlet("/login")
public class login extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public login() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WSUsuariosService wsu = new WSUsuariosService();
        WSUsuarios port = wsu.getWSUsuariosPort();
        
        request.setCharacterEncoding("UTF-8");
        String ing = (String) request.getParameter("usuarioIngresado");
        String con = (String) request.getParameter("contraseñaIngresada");    
        
        if (ing==null) { //Quiere cerrar sesión
        	if(request.getHeader("User-Agent").contains("Mobi")){
        		request.getSession().invalidate();
        		request.getRequestDispatcher("/WEB-INF/JSPs/inicioSesion.jsp").forward(request, response);
	       	}
	       	else {
	       		request.getSession().invalidate();
	            response.sendRedirect("index");
	       	}         
        }
        else { 
        	WSActividadesService wsa = new WSActividadesService();
            WSActividades port2 = wsa.getWSActividadesPort(); 	
            List<StringDTCategoriaMap.Entry> catsMenu = port2.obtenerCategorias().getEntry();
        	List<StringDTDepartamentoMap.Entry> depsMenu = port2.obtenerDepartamentos().getEntry();
        	request.setAttribute("depsMenu", depsMenu);
        	request.setAttribute("catsMenu", catsMenu);
        	
        	if (port.existeUsuarioNicknameEmail(ing)) {
	            if (port.contraCorrecta(ing, con)) {
	            	DtUsuario user = port.darDatosUsuarioConEmailONickname(ing);
	            	if(request.getHeader("User-Agent").contains("Mobi") && DtProveedor.class.isInstance(user)){
	            	     String error = "movilProveedor";
	            	     request.setAttribute("error", error);
	                     request.getRequestDispatcher("/WEB-INF/JSPs/inicioSesion.jsp").forward(request, response);
	            	}
	            	else {
		                request.getSession().setAttribute("datosUsuarioSesion", user);
		                response.sendRedirect("index");
	            	}
	            }else {
	                String error = "contraseña";
	                request.setAttribute("error", error);
	                request.getRequestDispatcher("/WEB-INF/JSPs/inicioSesion.jsp").forward(request, response);
	            }
	        }
	        else {
	            String error = "usuario";
	            request.setAttribute("error", error);
	            request.getRequestDispatcher("/WEB-INF/JSPs/inicioSesion.jsp").forward(request, response);
	        }
        }
    }

}


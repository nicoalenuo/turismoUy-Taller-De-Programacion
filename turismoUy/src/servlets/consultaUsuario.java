package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import servidoractividades.SetOfDTActividad;
import servidoractividades.StringDTCategoriaMap;
import servidoractividades.StringDTDepartamentoMap;
import servidoractividades.WSActividades;
import servidoractividades.WSActividadesService;
import servidorusuarios.CampoIncompletoException_Exception;
import servidorusuarios.DtProveedor;
import servidorusuarios.DtTurista;
import servidorusuarios.DtUsuario;
import servidorusuarios.StringDTActividadMap;
import servidorusuarios.StringDTUsuarioMap;
import servidorusuarios.WSUsuarios;
import servidorusuarios.WSUsuariosService;

@WebServlet("/consultaUsuario")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize= 1024 * 1024 * 100
)
public class consultaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public consultaUsuario() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	if (req.getHeader("User-Agent").contains("Mobi")) {
    		resp.sendRedirect("home");
    	} else {
    		WSActividadesService wsa = new WSActividadesService();
            WSActividades port2 = wsa.getWSActividadesPort(); 	
            List<StringDTCategoriaMap.Entry> catsMenu = port2.obtenerCategorias().getEntry();
        	List<StringDTDepartamentoMap.Entry> depsMenu = port2.obtenerDepartamentos().getEntry();
        	req.setAttribute("depsMenu", depsMenu);
        	req.setAttribute("catsMenu", catsMenu);
        	
            WSUsuariosService wsu = new WSUsuariosService();
            WSUsuarios port = wsu.getWSUsuariosPort();      
            
    		String idUser = (String) req.getParameter("id");
    		
    		if (idUser == null) {
    			StringDTUsuarioMap usuarios = port.obtenerUsuariosNickname();
    			req.setAttribute("usuariosListar", usuarios);
    			req.getRequestDispatcher("/WEB-INF/JSPs/listadoUsuarios.jsp").forward(req, resp);
    		}
    		else {
    			DtUsuario userLog = (DtUsuario) req.getSession().getAttribute("datosUsuarioSesion");
    			boolean seguible = userLog != null && !userLog.getNickname().equals(idUser);
    			req.setAttribute("seguible", seguible);
    			boolean yaLoSigue = userLog!=null && port.usuarioSigueA(userLog.getNickname(), idUser);
    			req.setAttribute("yaLoSigue", yaLoSigue);
    			
    			String error = req.getParameter("error");
    			if (error!=null && error.equals("contrasena")) {
    				String errorA = "errorContraseña";
    				req.setAttribute("error", errorA);
    			}
    			else if (error!=null && error.equals("campo")) {
    				String errorA = "errorContraseña";
    				req.setAttribute("error", errorA);
    			}
    			else if (error!=null && error.equals("actEnPaquete")) {
    				String errorA = "actEnPaquete";
    				req.setAttribute("error", errorA);
    			}
    			else if (error!=null && error.equals("salidaVigente")) {
    				String errorA = "salidaVigente";
    				req.setAttribute("error", errorA);
    			}
    			DtUsuario dtu = port.darUsuarioNick(idUser);
    			StringDTUsuarioMap usuariosSeguidos = port.darDatosUsuariosSeguidos(idUser);
    			StringDTUsuarioMap usuariosQueSiguen = port.darDatosUsuariosQueSiguen(idUser);
    			req.setAttribute("usuariosSeguidos", usuariosSeguidos);
    			req.setAttribute("usuariosQueSiguen", usuariosQueSiguen);
    			
    			if (DtTurista.class.isInstance(dtu)) {
    				req.setAttribute("salidasInscripto", port.obtenerSalidasInscripto(idUser));
    				if (userLog !=null && userLog.getNickname().equals(idUser)) {
    					req.setAttribute("paquetesComprados", port.obtenerDatosPaquetesComprados(idUser));
    				}
    			} else {
    				StringDTActividadMap acts = null;
    				if (userLog !=null && userLog.getNickname().equals(idUser)) {
    					acts = port.obtenerActividadesDeProveedor(idUser);
    					SetOfDTActividad actsF = port2.obtenerActividadesFinalizadasDeProv(userLog.getNickname());
    					req.setAttribute("actividadesProvistas", acts);
    					req.setAttribute("actividadesFinalizadas", actsF);
    				} else {
    					acts = port.obtenerActividadesConfirmadasDeProveedor(idUser);
    					req.setAttribute("actividadesProvistas", acts);
    				}
    				req.setAttribute("salidasParaActsProvistas", port.obtenerSalidasDeActividades(acts));
    			}
    			req.setAttribute("usuarioConsulta", dtu);
    			req.getRequestDispatcher("/WEB-INF/JSPs/consultaUsuario.jsp").forward(req, resp);
    		}
    	}	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    WSUsuariosService wsu = new WSUsuariosService();
        WSUsuarios port = wsu.getWSUsuariosPort();
		req.setCharacterEncoding("UTF-8");
		DtUsuario userLog = (DtUsuario) req.getSession().getAttribute("datosUsuarioSesion");
		
		if(req.getParameter("usuarioSeguir") != null) {
			String usuarioSeguir = req.getParameter("usuarioSeguir");
			port.seguirUsuario(userLog.getNickname(), usuarioSeguir);
			resp.sendRedirect("consultaUsuario?id="+usuarioSeguir);
		} else if (req.getParameter("usuarioDejarDeSeguir") != null) {
			String usuarioDejarDeSeguir = req.getParameter("usuarioDejarDeSeguir");
			port.dejarDeSeguirUsuario(userLog.getNickname(), usuarioDejarDeSeguir);
			resp.sendRedirect("consultaUsuario?id="+usuarioDejarDeSeguir);
		} else {
			String nombre = (String) req.getParameter("nombre");
			String apellido = (String) req.getParameter("apellido");
			String contraseña = (String) req.getParameter("contraseña");
			String confirmacion = (String) req.getParameter("confirmacion");
			String email = (String) req.getParameter("email");
			String fechaN = (String) req.getParameter("fechaN");			
			String nickname = (String) req.getParameter("nickname");
	
			try {		
				
				if (!contraseña.equals(confirmacion))
					throw new IllegalArgumentException();
				
				GregorianCalendar fecha = new GregorianCalendar();	
				Date date=null;
				try {
					date = new SimpleDateFormat("dd/MM/yyyy").parse(fechaN);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				fecha.setTime(date);
				
				XMLGregorianCalendar fechaXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(fecha);
				
				if (req.getParameter("tipoUs").equals("Turista")) {
				    String nacionalidad = req.getParameter("nacionalidad");
	                
	                DtTurista nuevoT = new DtTurista();
	                nuevoT.setNickname(nickname);
	                if (contraseña.isEmpty())
	                    contraseña = null;
	                nuevoT.setContra(contraseña);
	                nuevoT.setNombre(nombre);
	                nuevoT.setApellido(apellido);
	                nuevoT.setEmail(email);
	                nuevoT.setNacionalidad(nacionalidad);
	                nuevoT.setFechaNac(fechaXML);
	                
	                port.modificarUsuario(nuevoT);
				}
				else {
				    String web = req.getParameter("web");
	                String descripcion = req.getParameter("desc");
	                
	                DtProveedor nuevoP = new DtProveedor();
	                nuevoP.setNickname(nickname);
	                if (contraseña.isEmpty())
	                    contraseña = null;
	                nuevoP.setContra(contraseña);           
	                nuevoP.setNombre(nombre);
	                nuevoP.setApellido(apellido);
	                nuevoP.setEmail(email);
	                nuevoP.setSitioWeb(web);
	                nuevoP.setDescripcion(descripcion);
	                nuevoP.setFechaNac(fechaXML);
	                
	                port.modificarUsuario(nuevoP);
				}	
				
				DtUsuario user = port.darUsuarioNick(nickname);
				req.getSession().setAttribute("datosUsuarioSesion", user);
				
				Part imagen = req.getPart("imagenSel");
				boolean tieneImg = !imagen.getSubmittedFileName().isEmpty();
				byte[] bImg = imagen.getInputStream().readAllBytes();
				port.guardarImg(tieneImg, nickname, bImg);
				
				resp.sendRedirect("consultaUsuario?id="+nickname);
				
			}
			catch(IllegalArgumentException e) {	
				resp.sendRedirect("consultaUsuario?id="+nickname+"&error=contrasena");
			}
			catch(CampoIncompletoException_Exception e) {
				//Es imposible que ocurra, el JSP se encarga de controlarlo	
				resp.sendRedirect("consultaUsuario?id="+nickname+"&error=campo");
			} catch (DatatypeConfigurationException e) {
	            e.printStackTrace();
	        }
		}
	}
}

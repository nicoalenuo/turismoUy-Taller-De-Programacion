package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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

import servidoractividades.StringDTCategoriaMap;
import servidoractividades.StringDTDepartamentoMap;
import servidoractividades.WSActividades;
import servidoractividades.WSActividadesService;
import servidorusuarios.CampoIncompletoException_Exception;
import servidorusuarios.ContrasenasNoCoincidenException_Exception;
import servidorusuarios.DtProveedor;
import servidorusuarios.DtTurista;
import servidorusuarios.DtUsuario;
import servidorusuarios.UsuarioRepetidoException_Exception;
import servidorusuarios.WSUsuarios;
import servidorusuarios.WSUsuariosService;

@WebServlet("/altaUsuario")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize= 1024 * 1024 * 100
)
public class altaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public altaUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DtUsuario log = (DtUsuario) request.getSession().getAttribute("datosUsuarioSesion");
        if (log!=null || request.getHeader("User-Agent").contains("Mobi"))
            response.sendRedirect("home");
        else {
        	WSActividadesService wsa = new WSActividadesService();
            WSActividades port2 = wsa.getWSActividadesPort(); 	
            List<StringDTCategoriaMap.Entry> catsMenu = port2.obtenerCategorias().getEntry();
        	List<StringDTDepartamentoMap.Entry> depsMenu = port2.obtenerDepartamentos().getEntry();
        	request.setAttribute("depsMenu", depsMenu);
        	request.setAttribute("catsMenu", catsMenu);
            request.getRequestDispatcher("/WEB-INF/JSPs/altaUsuario.jsp").forward(request, response);          
        }
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		WSUsuariosService wsu = new WSUsuariosService();
        WSUsuarios port = wsu.getWSUsuariosPort();
        
        PrintWriter w = resp.getWriter();
		if (req.getParameter("email") == null) {
			boolean corr = port.existeUsuarioNickname(req.getParameter("nickname"));
			if (corr)
				w.write("Error");
			else 
				w.write("Correcto");
		}
		else if (req.getParameter("nickname") == null) {
			boolean corr = port.existeUsuarioEmail(req.getParameter("email"));
			if (corr)
				w.write("Error");
			else 
				w.write("Correcto");
		}
		else {
			try {	
				
				String nickname = (String) req.getParameter("nickname");
				String nombre = (String) req.getParameter("nombre");
				String apellido = (String) req.getParameter("apellido");
				String contraseña = (String) req.getParameter("contraseña");
				String confirmacion = (String) req.getParameter("confirmacion");
				String email = (String) req.getParameter("email");
				String fechaN = (String) req.getParameter("fechaN");
					
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
					nuevoT.setContra(contraseña);
					nuevoT.setNombre(nombre);
					nuevoT.setApellido(apellido);
					nuevoT.setEmail(email);
					nuevoT.setNacionalidad(nacionalidad);
					nuevoT.setFechaNac(fechaXML);
					
					port.confirmarAltaUsuario(nuevoT, confirmacion);
				}
				else {
					String web = req.getParameter("web");
					String descripcion = req.getParameter("desc");
					
					DtProveedor nuevoP = new DtProveedor();
	                nuevoP.setNickname(nickname);
	                nuevoP.setContra(contraseña);
	                nuevoP.setNombre(nombre);
	                nuevoP.setApellido(apellido);
	                nuevoP.setEmail(email);
	                nuevoP.setSitioWeb(web);
	                nuevoP.setDescripcion(descripcion);
	                nuevoP.setFechaNac(fechaXML);
	                
	                port.confirmarAltaUsuario(nuevoP, confirmacion);
				}
							
				Part imagen = req.getPart("imagenSel");
				boolean tieneImg = !imagen.getSubmittedFileName().isEmpty();
				byte[] bImg = imagen.getInputStream().readAllBytes();
				port.guardarImg(tieneImg, nickname, bImg);
				
				resp.sendRedirect("home");
			}
			catch(CampoIncompletoException_Exception e) {
				String error = "errorCampo";
				req.setAttribute("error", error);
				req.getRequestDispatcher("/WEB-INF/JSPs/altaUsuario.jsp").forward(req, resp);
			}
			catch(UsuarioRepetidoException_Exception e2) {
				String error = "errorUsuarios";
				req.setAttribute("error", error);
				req.getRequestDispatcher("/WEB-INF/JSPs/altaUsuario.jsp").forward(req, resp);
			}
			catch(ContrasenasNoCoincidenException_Exception e3) {
				String error = "errorContraseña";
				req.setAttribute("error", error);
				req.getRequestDispatcher("/WEB-INF/JSPs/altaUsuario.jsp").forward(req, resp);
			} catch (DatatypeConfigurationException e) {
	            e.printStackTrace();
	        }
		}
	}
}

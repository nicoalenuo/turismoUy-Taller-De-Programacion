package servlets;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.util.Date;

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
import servidorsalidas.ActividadInvalidaException_Exception;
import servidorsalidas.CantidadTuristaException_Exception;
import servidorsalidas.DtSalida;
import servidorsalidas.SalidaRepetidaException_Exception;
import servidorsalidas.StringDTActividadMap;
import servidoractividades.WSActividades;
import servidoractividades.WSActividadesService;
import servidorsalidas.WSSalidas;
import servidorsalidas.WSSalidasService;
import servidorusuarios.DtTurista;
import servidorusuarios.DtUsuario;

@WebServlet("/altaSalida")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize= 1024 * 1024 * 100
)

public class altaSalida extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public altaSalida() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    DtUsuario log = (DtUsuario) request.getSession().getAttribute("datosUsuarioSesion");
        if (log==null || DtTurista.class.isInstance(log))
            response.sendRedirect("home");
        else {  
        	WSActividadesService wsa = new WSActividadesService();
            WSActividades port2 = wsa.getWSActividadesPort(); 	
            List<StringDTCategoriaMap.Entry> catsMenu = port2.obtenerCategorias().getEntry();
        	List<StringDTDepartamentoMap.Entry> depsMenu = port2.obtenerDepartamentos().getEntry();
        	request.setAttribute("depsMenu", depsMenu);
        	request.setAttribute("catsMenu", catsMenu);
        	WSSalidasService wss = new WSSalidasService();
            WSSalidas port = wss.getWSSalidasPort(); 
            
    		StringDTDepartamentoMap deps= port2.obtenerDepartamentos();
    		request.setAttribute("departamentoListar", deps);
    		String depSeleccionado = request.getParameter("departamento");
    			
    		if (depSeleccionado == null) {
    			request.getRequestDispatcher("/WEB-INF/JSPs/altaSalida.jsp").forward(request, response);
    		}  else {
    			request.setAttribute("departamentoSeleccionado", depSeleccionado);
    			StringDTActividadMap acts= port.obtenerActividadesConfirmadasDelDepartamento(depSeleccionado);
    			request.setAttribute("actividadesListar", acts);
    			request.getRequestDispatcher("/WEB-INF/JSPs/altaSalida.jsp").forward(request, response);
    		}
        }
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			String nombre = (String) request.getParameter("nombre");
			String fechaS = (String) request.getParameter("fechaS");
			String hora = (String) request.getParameter("hora");
			String lugar = (String) request.getParameter("lugar");
			String actividad= (String) request.getParameter("actividad");
			int cantMax = Integer.valueOf((String) request.getParameter("cantMax"));
			DtUsuario dtu= (DtUsuario) request.getSession().getAttribute("datosUsuarioSesion");
			String email= dtu.getEmail();
			WSSalidasService wss = new WSSalidasService();
            WSSalidas port = wss.getWSSalidasPort(); 
			GregorianCalendar fecha = new GregorianCalendar();	
			Date date=null;

			try {
				date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(fechaS+" "+hora);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			fecha.setTime(date);

			try {
				DtSalida nueva = new DtSalida();
				nueva.setNombre(nombre);
				nueva.setCantMax(cantMax);
				nueva.setCantInscriptos(0);
				XMLGregorianCalendar fechaAltaXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
				nueva.setFechaAlta(fechaAltaXML);
				XMLGregorianCalendar fechaXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(fecha);
				nueva.setFechaSalida(fechaXML);
				nueva.setLugarSalida(lugar);
				port.ingresarSalida(actividad, nueva);
				
				Part imagen = request.getPart("imagen");
				boolean tieneImg = !imagen.getSubmittedFileName().isEmpty();
				byte[] bImg = imagen.getInputStream().readAllBytes();
				port.guardarImg(tieneImg, nueva.getNombre(), bImg);
				
				response.sendRedirect("home");
			} catch (SalidaRepetidaException_Exception e2) {
				response.sendRedirect("altaSalida?error=nombre");
			}  catch (ActividadInvalidaException_Exception e3) {
				response.sendRedirect("altaSalida?error=actividad");
			} catch (CantidadTuristaException_Exception e4) {
				response.sendRedirect("altaSalida?error=cantidad");
			}catch (DatatypeConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}

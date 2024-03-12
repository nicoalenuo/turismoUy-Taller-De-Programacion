package servlets;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import servidorsalidas.StringDTActividadMap;
import servidoractividades.StringDTCategoriaMap;
import servidoractividades.StringDTDepartamentoMap;
import servidoractividades.WSActividades;
import servidoractividades.WSActividadesService;
import servidorsalidas.ActividadInvalidaException_Exception;
import servidorsalidas.CantidadTuristaException_Exception;
import servidorsalidas.DepartamentoInvalidoException_Exception;
import servidorsalidas.InscripcionExistenteException_Exception;
import servidorsalidas.PaqueteInvalidoException_Exception;
import servidorsalidas.SalidaInvalidaException_Exception;
import servidorsalidas.SalidaLlenaException_Exception;
import servidorsalidas.StringDTPaqueteMap;
import servidorsalidas.StringDTSalidaMap;
import servidorsalidas.WSSalidas;
import servidorsalidas.WSSalidasService;
import servidorusuarios.DtProveedor;
import servidorusuarios.DtUsuario;

@WebServlet("/inscripcionSalida")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize= 1024 * 1024 * 100
)

public class inscripcionSalida extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public inscripcionSalida() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DtUsuario log = (DtUsuario) request.getSession().getAttribute("datosUsuarioSesion");
         if (log==null || DtProveedor.class.isInstance(log) || request.getHeader("User-Agent").contains("Mobi"))
            response.sendRedirect("home");
        else {
        	request.setCharacterEncoding("UTF-8");
        	WSActividadesService wsa = new WSActividadesService();
            WSActividades port = wsa.getWSActividadesPort(); 	
            List<StringDTCategoriaMap.Entry> catsMenu = port.obtenerCategorias().getEntry();
        	List<StringDTDepartamentoMap.Entry> depsMenu = port.obtenerDepartamentos().getEntry();
        	request.setAttribute("depsMenu", depsMenu);
        	request.setAttribute("catsMenu", catsMenu);
        	
    		StringDTDepartamentoMap deps= port.obtenerDepartamentos();
    		request.setAttribute("departamentoListar", deps);
            
            String depSeleccionado = (String) request.getParameter("departamento");
            String actSeleccionada = (String) request.getParameter("actividad");
    		String salidaSeleccionada = (String) request.getParameter("salida");
            DtUsuario dtu= (DtUsuario) request.getSession().getAttribute("datosUsuarioSesion");
            String email= dtu.getEmail();
            
            WSSalidasService wss = new WSSalidasService();
            WSSalidas port2 = wss.getWSSalidasPort();
            
    			
    		if (depSeleccionado == null) {
    			request.getRequestDispatcher("/WEB-INF/JSPs/inscripcionSalida.jsp").forward(request, response);
    		}  else if (actSeleccionada == null) {
    			request.setAttribute("departamentoSeleccionado", depSeleccionado);
    			StringDTActividadMap acts= port2.obtenerActividadesConfirmadasDelDepartamento(depSeleccionado);
    			request.setAttribute("actividadesListar", acts);
    			request.getRequestDispatcher("/WEB-INF/JSPs/inscripcionSalida.jsp").forward(request, response);
    		} else {
    			request.setAttribute("departamentoSeleccionado", depSeleccionado);
                request.setAttribute("actividadSeleccionada", actSeleccionada);
                StringDTActividadMap acts= port2.obtenerActividadesConfirmadasDelDepartamento(depSeleccionado);
    			request.setAttribute("actividadesListar", acts);
        		
    			if (salidaSeleccionada != null) {
        			request.setAttribute("salidaSeleccionada", salidaSeleccionada);
        		}
    			
    			StringDTSalidaMap salidas = port2.listarSalidasVigentes(actSeleccionada);

    			request.setAttribute("salidasListar", salidas);
    			String nickname= dtu.getNickname();
    
    			StringDTPaqueteMap paquetes = port2.obtenerDatosPaquetesDeActividad(actSeleccionada);
    			StringDTPaqueteMap paquetesUsuario = port2.filtrarPaquetesUsuario(paquetes, nickname);
    			request.setAttribute("paquetesListar", paquetesUsuario);
                request.getRequestDispatcher("/WEB-INF/JSPs/inscripcionSalida.jsp").forward(request, response);
            }
        }
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    request.setCharacterEncoding("UTF-8");
			int cantTuristas = Integer.valueOf((String) request.getParameter("cantTuristas"));
			String salidaSeleccionada = (String) request.getParameter("salida");
			String actividadSeleccionada = (String) request.getParameter("actividad");
			String departamentoSeleccionado = (String) request.getParameter("departamento");
			String paqSeleccionado = (String) request.getParameter("paquete");
			
			WSSalidasService wss = new WSSalidasService();
            WSSalidas port = wss.getWSSalidasPort();
            
			DtUsuario dtu= (DtUsuario) request.getSession().getAttribute("datosUsuarioSesion");
			String nickname= dtu.getNickname();
			
		try {
			XMLGregorianCalendar fechaXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
			port.inscribirTurista(nickname, cantTuristas, fechaXML, salidaSeleccionada, paqSeleccionado, departamentoSeleccionado, actividadSeleccionada);
			response.sendRedirect("home");
		} catch (SalidaLlenaException_Exception e1) {
			response.sendRedirect("inscripcionSalida?error=errorSalida");
		} catch(DepartamentoInvalidoException_Exception e2) {
			response.sendRedirect("inscripcionSalida?error=errorDepartamento");
		} catch(SalidaInvalidaException_Exception e3) {
			response.sendRedirect("inscripcionSalida?error=errorSalida2");
		} catch(PaqueteInvalidoException_Exception e4) {
			response.sendRedirect("inscripcionSalida?error=errorPaquete");
		} catch(ActividadInvalidaException_Exception e5) {
			response.sendRedirect("inscripcionSalida?error=errorActividad");
		} catch(IllegalArgumentException e6) {
			response.sendRedirect("inscripcionSalida?error=errorSalida3");
		} catch(CantidadTuristaException_Exception e7) {
            response.sendRedirect("inscripcionSalida?error=errorCantidadTuristas");
        } catch(InscripcionExistenteException_Exception e8) {
            response.sendRedirect("inscripcionSalida?error=errorSalida3");
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        } 
		 
	}
}

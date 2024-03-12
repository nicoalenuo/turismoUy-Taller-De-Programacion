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

import servidorpaquetes.CantidadTuristaException_Exception;
import servidorpaquetes.PaqueteCompradoException_Exception;
import servidorpaquetes.PaqueteInvalidoException_Exception;
import servidorpaquetes.WSPaquetes;
import servidorpaquetes.WSPaquetesService;
import servidoractividades.StringDTCategoriaMap;
import servidoractividades.StringDTDepartamentoMap;
import servidoractividades.WSActividades;
import servidoractividades.WSActividadesService;
import servidorpaquetes.StringDTPaqueteMap;
import servidorusuarios.DtProveedor;
import servidorusuarios.DtUsuario;

@WebServlet("/compraPaquete")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize= 1024 * 1024 * 100
)
public class compraPaquete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public compraPaquete() {
        super();
        // TODO Auto-generated constructor stub
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
        	
        	WSPaquetesService wsp = new WSPaquetesService();
            WSPaquetes port2 = wsp.getWSPaquetesPort();	
            
    		StringDTPaqueteMap paquetes= port2.obtenerPaquetesConAlmenosUnaActividad();
    		request.setAttribute("paquetes", paquetes);
        	request.getRequestDispatcher("/WEB-INF/JSPs/compraPaquete.jsp").forward(request, response);
        }
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		DtUsuario dtu= (DtUsuario) req.getSession().getAttribute("datosUsuarioSesion");
		String idUser = dtu.getNickname();
		
		WSPaquetesService wsp = new WSPaquetesService();
        WSPaquetes port = wsp.getWSPaquetesPort();
		try {	
			String paquetes= (String) req.getParameter("paquetes");
			int cantTur= Integer.parseInt((String) req.getParameter("cantTur"));
			XMLGregorianCalendar fechaXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
			port.comprarPaquete(idUser, cantTur, fechaXML, paquetes);
			
			resp.sendRedirect("home");
		}
		catch(NumberFormatException e) {
			resp.sendRedirect("compraPaquete?error=cantidad");
		}
		catch(PaqueteCompradoException_Exception e) {
			resp.sendRedirect("compraPaquete?error=compra");
		}
		catch(CantidadTuristaException_Exception e) {
			resp.sendRedirect("compraPaquete?error=cantidad");
		}
		catch(PaqueteInvalidoException_Exception e) {
			resp.sendRedirect("compraPaquete?error=paquete");
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

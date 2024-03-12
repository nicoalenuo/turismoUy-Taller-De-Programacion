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
import javax.servlet.http.Part;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import servidoractividades.StringDTCategoriaMap;
import servidoractividades.StringDTDepartamentoMap;
import servidoractividades.WSActividades;
import servidoractividades.WSActividadesService;
import servidoractividades.EstadoAct;
import servidorusuarios.DtTurista;
import servidorusuarios.DtUsuario;
import servidoractividades.ActividadRepetidaException_Exception;
import servidoractividades.CategoriaInvalidaException_Exception;
import servidoractividades.CostoEsCeroException_Exception;
import servidoractividades.DepartamentoInvalidoException_Exception;
import servidoractividades.DtActividad;
import servidoractividades.DuracionEsCeroException_Exception;
import net.java.dev.jaxb.array.StringArray;

@WebServlet("/altaActividad")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize= 1024 * 1024 * 100
)
public class altaActividad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public altaActividad() {
        super();
    }

	
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DtUsuario log = (DtUsuario) request.getSession().getAttribute("datosUsuarioSesion");
        if (log==null || DtTurista.class.isInstance(log))
            response.sendRedirect("home");
        else {    
        	WSActividadesService wsa = new WSActividadesService();
            WSActividades port = wsa.getWSActividadesPort(); 	
            List<StringDTCategoriaMap.Entry> catsMenu = port.obtenerCategorias().getEntry();
        	List<StringDTDepartamentoMap.Entry> depsMenu = port.obtenerDepartamentos().getEntry();
        	request.setAttribute("depsMenu", depsMenu);
        	request.setAttribute("catsMenu", catsMenu);
    		StringDTDepartamentoMap deps= port.obtenerDepartamentos();
    		request.setAttribute("departamentoListar", deps);
    		StringDTCategoriaMap cats= port.obtenerCategorias();
    		request.setAttribute("categoriaListar", cats);
    		request.getRequestDispatcher("/WEB-INF/JSPs/altaActividad.jsp").forward(request, response);
    	}
    }
    
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NumberFormatException, NullPointerException {
		req.setCharacterEncoding("UTF-8");
		String nombre = (String) req.getParameter("nombre");
		String descripcion = (String) req.getParameter("descripcion");
		String ciudad = (String) req.getParameter("ciudad");
		String[] checked = (String[]) req.getParameterValues("categoria"); //Si el array es vacio, checked llegara como null
		DtUsuario dtu= (DtUsuario) req.getSession().getAttribute("datosUsuarioSesion");
		String email= (String) dtu.getEmail();
		String urlVideo= (String) req.getParameter("urlVideo");
		
		
		try {
			String dpto= (String) req.getParameter("departamentos");
			WSActividadesService wsa = new WSActividadesService();
	        WSActividades port = wsa.getWSActividadesPort();
        	
			int contador=0;
			StringArray categorias= new StringArray();
			if (checked != null) {
				while (contador<checked.length) {
					categorias.getItem().add((String) checked[contador]);
					contador++;
				}
			}
			
			float duracion= Float.parseFloat((String) req.getParameter("duracion"));
			float costo= Float.parseFloat((String) req.getParameter("costo"));
								
			DtActividad nuevo= new DtActividad();
			nuevo.setNombre(nombre);
			nuevo.setDescripcion(descripcion);
			nuevo.setDuracion(duracion);
			nuevo.setCostoTurista(costo);
			nuevo.setCiudad(ciudad);
			XMLGregorianCalendar fechaXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
			nuevo.setFechaAlta(fechaXML);
			nuevo.setEstado(EstadoAct.AGREGADA);
			nuevo.setUrlVideo(urlVideo);
			nuevo.setCantTuristasFavoritos((int) 0);
			port.confirmarAltaActividad(email, dpto, nuevo, categorias);
			
			Part imagen = req.getPart("imagen");
			boolean tieneImg = !imagen.getSubmittedFileName().isEmpty();
			byte[] bImg = imagen.getInputStream().readAllBytes();
			port.guardarImg(tieneImg, nuevo.getNombre(), bImg);

			
			resp.sendRedirect("home");
			
		}
		catch(NumberFormatException e) {
			resp.sendRedirect("altaActividad?error=numero");			
		}
		catch(NullPointerException e2) {
			e2.printStackTrace();
		}
		catch(CostoEsCeroException_Exception e3) {
			resp.sendRedirect("altaActividad?error=costo");
		}
		catch(DepartamentoInvalidoException_Exception e4) {
			resp.sendRedirect("altaActividad?error=departamento");
		}
		catch(ActividadRepetidaException_Exception e5) {
			resp.sendRedirect("altaActividad?error=actividad");
		}
		catch(CategoriaInvalidaException_Exception e6) {
			resp.sendRedirect("altaActividad?error=categoria");
		}
		catch(DuracionEsCeroException_Exception e3) {
			resp.sendRedirect("altaActividad?error=duracion");
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}							
	}
}
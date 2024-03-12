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
import servidoractividades.DtActividad;
import servidoractividades.SetOfString;
import servidoractividades.StringDTActividadMap;
import servidorusuarios.DtTurista;
import servidorusuarios.DtUsuario;
import servidoractividades.StringDTPaqueteMap;
import servidoractividades.StringDTSalidaMap;

@WebServlet("/consultaActividad")
public class consultaActividad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public consultaActividad() {
        super();
    }
	
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	WSActividadesService wsa = new WSActividadesService();
        WSActividades port = wsa.getWSActividadesPort(); 	
        List<StringDTCategoriaMap.Entry> catsMenu = port.obtenerCategorias().getEntry();
    	List<StringDTDepartamentoMap.Entry> depsMenu = port.obtenerDepartamentos().getEntry();
    	req.setAttribute("depsMenu", depsMenu);
    	req.setAttribute("catsMenu", catsMenu);
    	
    	String idAct = (String) req.getParameter("id");
    	String idF = (String) req.getParameter("idF");
    	
    	String tipo = (String) req.getParameter("tipo");
        SetOfString actividadesFavoritas= null;
        StringDTActividadMap actividades= null;
        DtUsuario dtu= (DtUsuario) req.getSession().getAttribute("datosUsuarioSesion");
        
    	if (tipo == null && idF == null) {
    		DtActividad actividad= port.darDatosActividad(idAct);
			SetOfString categorias= port.obtenerCategoriasDeActividad(actividad.getNombre()); //nunca es vacio
			StringDTSalidaMap salidas= port.obtenerSalidasDeActividad(actividad.getNombre()); //puede ser vacio
			StringDTPaqueteMap paquetes= port.obtenerDatosPaquetesDeActividad(actividad.getNombre()); //puede ser vacio
			Boolean esFavorito= false;
			if(dtu != null && DtTurista.class.isInstance(dtu)) {
				esFavorito= port.esFavorito(dtu.getNickname(), actividad.getNombre());
			}
			req.setAttribute("actividad", actividad);
			req.setAttribute("categorias", categorias);
			req.setAttribute("salidas", salidas);
			req.setAttribute("paquetes", paquetes);
			req.setAttribute("esFavorito", esFavorito);
			req.getRequestDispatcher("/WEB-INF/JSPs/consultaActividad.jsp").forward(req, resp);
    	}else if (tipo.equals("departamento")) {
			req.setAttribute("departamento", idAct);
			req.setAttribute("tipo", tipo);
			actividades= port.obtenerActividadesConfirmadasDelDepartamento(idAct);
			if(dtu!=null && !actividades.getEntry().isEmpty() && DtTurista.class.isInstance(dtu)) {
				actividadesFavoritas = port.obtenerActividadesFavoritas(dtu.getNickname(),actividades);
			}
			req.setAttribute("actividadesFavoritas", actividadesFavoritas);
			req.setAttribute("actividadesListar", actividades);
			req.getRequestDispatcher("/WEB-INF/JSPs/listadoActividad.jsp").forward(req, resp);
		}else if (tipo.equals("categoria")) {
			req.setAttribute("categoria", idAct);
			req.setAttribute("tipo", tipo);
			actividades= port.obtenerActividadesConfirmadasDeCategoria(idAct);
			if(dtu!=null && !actividades.getEntry().isEmpty() && DtTurista.class.isInstance(dtu)) {
				actividadesFavoritas = port.obtenerActividadesFavoritas(dtu.getNickname(),actividades);
			}
			req.setAttribute("actividadesFavoritas", actividadesFavoritas);
			req.setAttribute("actividadesListar", actividades);

			req.getRequestDispatcher("/WEB-INF/JSPs/listadoActividad.jsp").forward(req, resp);
		}
	}
    
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		WSActividadesService wsa = new WSActividadesService();
        WSActividades port = wsa.getWSActividadesPort();
        
        DtUsuario dtu= (DtUsuario) req.getSession().getAttribute("datosUsuarioSesion");
		String esFavorito= (String) req.getParameter("favorito");
		String idAct = (String) req.getParameter("idAct");
		if(esFavorito.equals("true")) {
			port.agregarActividadFavorita(dtu.getNickname(), idAct);
		}else{
			port.quitarActividadFavorita(dtu.getNickname(), idAct);
		}
		resp.sendRedirect("consultaActividad?id="+idAct);
    }
}

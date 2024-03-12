package servlets;

import java.io.IOException;
import java.util.GregorianCalendar;
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
import servidorsalidas.DtSalida;
import servidorsalidas.WSSalidas;
import servidorsalidas.WSSalidasService;
import servidorusuarios.DtTurista;
import servidorusuarios.DtUsuario;

@WebServlet("/consultaSalida")
public class consultaSalida extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public consultaSalida() {
        super();
    }
	
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	WSActividadesService wsa = new WSActividadesService();
        WSActividades port2 = wsa.getWSActividadesPort(); 	
        List<StringDTCategoriaMap.Entry> catsMenu = port2.obtenerCategorias().getEntry();
    	List<StringDTDepartamentoMap.Entry> depsMenu = port2.obtenerDepartamentos().getEntry();
    	request.setAttribute("depsMenu", depsMenu);
    	request.setAttribute("catsMenu", catsMenu);
    	
    	WSSalidasService wss = new WSSalidasService();
        WSSalidas port = wss.getWSSalidasPort();
    	
    	String idSal = (String) request.getParameter("id");

        Boolean esTurista = false;
        DtUsuario dtu= (DtUsuario) request.getSession().getAttribute("datosUsuarioSesion");
        if (dtu != null) {
            esTurista = DtTurista.class.isInstance(dtu);
        }

    	DtSalida salida= port.datosSalida(idSal);
        Boolean valida = salida.getFechaSalida().toGregorianCalendar().compareTo(new GregorianCalendar()) != -1;
        String deptoSal = port.darDepSal(idSal);
        String actSal = port.darNombreActSal(idSal);
        
        request.setAttribute("departamento", deptoSal);
        request.setAttribute("actividad", actSal);
        request.setAttribute("salidaValida", valida);
        request.setAttribute("usuarioTurista", esTurista);
        request.setAttribute("salida", salida);
        request.setAttribute("fechaSalida", salida.getFechaSalida());
        String horaReal= "";
        if(salida.getFechaSalida().toGregorianCalendar().get(GregorianCalendar.HOUR_OF_DAY)<=9) {
        	horaReal= "0";
        }
        horaReal= horaReal+Integer.toString(salida.getFechaSalida().toGregorianCalendar().get(GregorianCalendar.HOUR_OF_DAY))+":";
        if(salida.getFechaSalida().toGregorianCalendar().get(GregorianCalendar.MINUTE)<=9) {
        	horaReal=horaReal+"0";
        }
        horaReal= horaReal+Integer.toString(salida.getFechaSalida().toGregorianCalendar().get(GregorianCalendar.MINUTE));
        request.setAttribute("hora",horaReal);
        request.setAttribute("cantMax", salida.getCantMax());
        request.setAttribute("lugar", salida.getLugarSalida());
        request.setAttribute("fechaAlta", salida.getFechaAlta());
        request.getRequestDispatcher("/WEB-INF/JSPs/consultaSalida.jsp").forward(request, response);
	}
}

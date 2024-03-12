package servlets;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import servidorsalidas.WSSalidas;
import servidorsalidas.WSSalidasService;
import servidorusuarios.WSUsuarios;
import servidorusuarios.WSUsuariosService;

@WebServlet("/descargaPDF")
public class descargaPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public descargaPDF() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			response.setContentType("application/pdf");
			WSSalidasService wss = new WSSalidasService();
	        WSSalidas port = wss.getWSSalidasPort();
			
			
			String nick = request.getParameter("id");
			String salida = request.getParameter("salida");
			String act = port.darNombreActSal(salida);
			String fecha = request.getParameter("fecha");
			String cant = request.getParameter("cant");
			
			Document document = new Document();
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
			document.add(new Paragraph("Comprobante de inscripcion a salida"));
			document.add(new Paragraph());
			document.add(new Paragraph("El/la usuari@ " + nick + " se inscribió en la fecha " + fecha + " a " + salida + " para realizar la actividad " + act));
			document.add(new Paragraph());
			document.add(new Paragraph("La inscripcion es para " +cant+ " persona/s"));
			document.add(new Paragraph("Muchas gracias por su compra =)"));
			
			document.close();		
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
	}

}

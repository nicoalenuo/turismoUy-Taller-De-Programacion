<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>

<html lang="en">
<head>
	

	<%@ page import="servidorsalidas.DtSalida" %>

	<%@ page import="java.util.*" %>

	<%
	DtSalida salida = (DtSalida) request.getAttribute("salida");
	String id = (String) salida.getNombre();
  String dpto = (String) request.getAttribute("departamento");
  String act = (String) request.getAttribute("actividad");
	String fechaAlta = Integer.toString(salida.getFechaAlta().toGregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH)) +"/"+ Integer.toString(salida.getFechaAlta().toGregorianCalendar().get(GregorianCalendar.MONTH)+1) +"/"+Integer.toString(salida.getFechaAlta().toGregorianCalendar().get(GregorianCalendar.YEAR));
	String fechaSalida = Integer.toString(salida.getFechaSalida().toGregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH)) +"/"+ Integer.toString(salida.getFechaSalida().toGregorianCalendar().get(GregorianCalendar.MONTH)+1) +"/"+Integer.toString(salida.getFechaSalida().toGregorianCalendar().get(GregorianCalendar.YEAR));
  String hora = (String) request.getAttribute("hora");
  String cantMax = Integer.toString(salida.getCantMax());
  String lugar = (String) request.getAttribute("lugar");
  Boolean esTurista = (Boolean) request.getAttribute("usuarioTurista");
  Boolean esValida = (Boolean) request.getAttribute("salidaValida");
  %>

  <jsp:include page="/WEB-INF/Template/head.jsp"/>

  <style>
    @media (max-width: 400px) {  
      #achicar{
        font-size:11px;
      }
    }
  </style>

</head>

<body>

  <jsp:include page="/WEB-INF/Template/header.jsp"/>

  <div style="height: 65px;"></div>
  <div class="container-fluid"  style="min-height: 1000px;">
    <div class="row">
      
      <jsp:include page="/WEB-INF/Template/menuIzq.jsp"/>
      
      <div class="col py-4">

        <div class="row border-bottom mb-3" style="max-width: 80rem">
          <div class="col">
              <h2>Salida</h2>
          </div>
        </div>
        <div class="row mx-3 flex-column flex-md-row">
          <img src="imagenes?tipo=salida&id=<%=id %>" class="card-img mr-3 mb-3" style="max-height: 300px; max-width: 400px;">
          <div class="col" >
            <h3 style="font-size: 25px; color: rgb(136, 31, 48);"><%=id%></h3>
            <% if (esTurista && esValida) { %>
            <a href="inscripcionSalida?departamento=<%=dpto%>&actividad=<%=act%>&salida=<%=id%>" class="btn btn-outline-danger mt-3"  style="min-width: 8rem;">Inscribirse</a>
            <%}%>
            </div>
        </div>           
        
        
        <div class="card text-center ml-3 mt-3" style="max-width: 800px;">
            <div class="card-header">
                <nav class="nav nav-pills card-header-pills flex-column flex-md-row">
                      <a class="nav-link active" id="linkD" href="#info" data-toggle="tab">Detalles</a>
                </nav>
              </div>
          
          <div class="tab-content py-4">
            <div class="tab-pane align-left active" id="info">
              <h5 class="card-title">Nombre</h5>
              <label><%=salida.getNombre() %></label>
              <h5 class="card-title">Fecha de salida</h5>
              <label><%=fechaSalida %></label>
              <h5 class="card-title">Hora</h5>
              <label><%=hora %></label>
              <h5 class="card-title">Cantidad Maxima de Turistas</h5>
              <label><%= cantMax %></label>
              <h5 class="card-title">Lugar de salida</h5>
              <label><%= lugar %></label>
              <h5 class="card-title">Fecha de alta</h5>
              <label><%=fechaAlta %></label>
            </div>
            
          </div>
        </div>
      </div>
  	</div>
  </div>
  
  <footer id="sticky-footer" class="py-4 bg-light text-black-50 border">
      <div class="container text-center">
        <small>Copyright &copy; turismo.Uy</small>
      </div>
  </footer>
  
  <script src="media/js/menuIzq.js"></script>  

</body>
</html>

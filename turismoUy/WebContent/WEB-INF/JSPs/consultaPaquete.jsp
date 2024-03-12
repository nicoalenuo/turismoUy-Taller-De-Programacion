<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>

<html lang="en">
<head>

  <jsp:include page="/WEB-INF/Template/head.jsp"/>

</head>

	<%@ page import="servidorpaquetes.DtActividad" %>
	<%@ page import="servidorpaquetes.DtPaquete" %>
	<%@ page import="servidorpaquetes.SetOfString" %>
	<%@ page import="servidorpaquetes.StringDTActividadMap" %>
	<%@ page import="servidorpaquetes.DtPaquete" %>
	
	<%@ page import="java.util.*" %>
 
<%

	DtPaquete dtpaq = (DtPaquete) request.getAttribute("dtpaq");
	StringDTActividadMap acts = (StringDTActividadMap) request.getAttribute("acts");
	SetOfString categorias = (SetOfString) request.getAttribute("categorias");
	
	GregorianCalendar gc = dtpaq.getFechaAlta().toGregorianCalendar();
	String fechaAlta = Integer.toString(gc.get(GregorianCalendar.DAY_OF_MONTH)) +"/"+ Integer.toString(gc.get(GregorianCalendar.MONTH)+1) +"/"+Integer.toString(gc.get(GregorianCalendar.YEAR));
	

%>

<body>

  <jsp:include page="/WEB-INF/Template/header.jsp"/>

  <div style="height: 65px;"></div>
  <div class="container-fluid"  style="min-height: 1000px;">
    <div class="row">
      
      <jsp:include page="/WEB-INF/Template/menuIzq.jsp"/>

      <div class="col py-4">

        <div class="row border-bottom mb-3" style="max-width: 80rem">
          <div class="col">
              <h2>Detalle del paquete</h2>
          </div>
        </div>
        <div class="row mx-3 flex-column flex-md-row">
            <img src="imagenes?tipo=paquete&id=<%=dtpaq.getNombre()%>" class="card-img mr-3 mb-3" style="max-height: 300px; max-width: 400px;">
            <div class="col">
                <h3><%=dtpaq.getNombre() %></h3>
                <label><%=dtpaq.getDescripcion() %> </label>
                  <div class="card border mt-3" style="max-width: 18rem;">
                    <div class="card-header"><span class="mr-2"><i class="fa fa-hashtag" style="color:rgb(220, 53, 69)"></i></span>Categorias</div>
                    <div class="card-body text-secondary">
                      <%
		                if(categorias!=null){
			             	Iterator<String> it = categorias.getItem().iterator();
			   			    String key;
			   			    while (it.hasNext()) {
			   					key = it.next();
			           
			                %>
			                <p class="card-text">
			                  <a href="consultaActividad?tipo=categoria&id=<%=key %>"><button type="button" id="btnCategorias" class="btn btn-sm m-1 border-danger" style="color:rgb(220, 53, 69)"><%=key %></button></a>
			                </p>
			                <%} 
			            }%>
                    </div>
                  </div>
            </div>
        </div>
        
        <div class="card text-center ml-3 mt-3" style="max-width: 800px;">
            <div class="card-header">
              <nav class="nav nav-pills card-header-pills flex-column flex-md-row">
                    <a class="nav-link active" id="linkD" href="#datos" data-toggle="tab">Datos básicos</a>
                    <a class="nav-link" id="linkD" href="#actividades" data-toggle="tab">Actividades turísticas del paquete</a>
              </nav>
            </div>
            <div class="tab-content py-4">
                <div class="tab-pane align-left active" id="datos">
                    <h5 class="card-title">Nombre</h5>
                    <label><%=dtpaq.getNombre() %></label>
                    <h5 class="card-title">Validez</h5>
                    <label><%=dtpaq.getPeriodoValidez() %> días</label>
                    <h5 class="card-title">Descuento</h5>
                    <label><%=dtpaq.getDescuento() %>%</label>
                    <h5 class="card-title">Fecha de Alta</h5>
                    <label><%=fechaAlta %></label>
                    <h5 class="card-title">Descripción</h5>
                    <label><%=dtpaq.getDescripcion() %></label>
                </div>
                
                <div class="tab-pane" id="actividades">
                  <div class="col col-lg-9 py-4">
                    <div class="container">
                      <div class="row g-3">
            			<% if(acts!=null){
            				
			             	Iterator<StringDTActividadMap.Entry> it = acts.getEntry().iterator();
			   			    StringDTActividadMap.Entry key;
			   			    DtActividad act = null;
			   			    while (it.hasNext()) {
			   					key = it.next();
			   					act = key.getValue();
            			%>
                        <div class="col-12 col-md-6 col-lg-4">
                          <div class="card my-2" style="max-width: 460px">
                            <img src="imagenes?tipo=actividad&id=<%=act.getNombre() %>" class="card-img-left" style="max-height: 300px">
                            <div class="card-body">
                                <h5 class="card-title"><%=act.getNombre() %></h5>
                                <p><%=act.getDescripcion() %></p>
                                <a href="consultaActividad?id=<%=act.getNombre()%>" class="btn btn-outline-secondary my-2 my-sm-0">Leer más</a>
                            </div>
                          </div>
                        </div>
                        <% }
			   			  }%>
                      </div>
                    </div>
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

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>

<html lang="en">
<head>
	
	<%@ page import="servidorusuarios.DtUsuario" %>
	<%@ page import="servidorusuarios.DtTurista" %>
	<%@ page import="servidoractividades.DtActividad" %>
	<%@ page import="servidoractividades.DtDepartamento" %>
	<%@ page import="servidoractividades.DtSalida" %>
	<%@ page import="servidoractividades.DtPaquete" %>
	<%@ page import="servidoractividades.SetOfString" %>
	<%@ page import="servidoractividades.StringDTSalidaMap" %>
	<%@ page import="servidoractividades.StringDTPaqueteMap" %>
	<%@ page import="java.util.*" %>

	<%
	boolean movil = request.getHeader("User-Agent").contains("Mobi");
	Boolean esFavorito = (Boolean) request.getAttribute("esFavorito");
	DtUsuario dtu= (DtUsuario) request.getSession().getAttribute("datosUsuarioSesion");
	DtActividad actividad = (DtActividad) request.getAttribute("actividad");
	List<String> cats = null;
	SetOfString categorias = (SetOfString) request.getAttribute("categorias");
	cats = categorias.getItem();
	StringDTSalidaMap salidas= (StringDTSalidaMap) request.getAttribute("salidas");
	StringDTPaqueteMap paquetes= (StringDTPaqueteMap) request.getAttribute("paquetes");
	String id = (String) actividad.getNombre();
	GregorianCalendar gc = actividad.getFechaAlta().toGregorianCalendar();
	String fechaAlta = Integer.toString(gc.get(GregorianCalendar.DAY_OF_MONTH)) +"/"+ Integer.toString(gc.get(GregorianCalendar.MONTH)+1) +"/"+Integer.toString(gc.get(GregorianCalendar.YEAR));
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
              <h2>Actividad</h2>
          </div>
        </div>
        
        <div class="row mx-3 flex-column flex-md-row">
          <img src="imagenes?tipo=actividad&id=<%=id%>" class="card-img mr-3 mb-3" style="max-height: 300px; max-width: 400px;">
          <div class="col">  
            <h3><%=id %></h3>
            <div class="card border mt-3" style="max-width: 18rem;">
              <div class="card-header"><span class="mr-2"><i class="fa fa-hashtag" style="color:rgb(220, 53, 69)"></i></span>Categorias</div>
              <div class="card-body text-secondary">
                <%
                if(!categorias.equals(null)){
	             	Iterator<String> it = cats.iterator();
	   			    String key;
	   			    while (it.hasNext()) {
	   					key = it.next();
	           
	                %>
	                <p class="card-text">
	                  <a href="consultaActividad?tipo=categoria&id=<%=key %>"><button type="button" id="btnCategorias" class="btn btn-sm m-1 border-danger" style="color:rgb(220, 53, 69)"><%=key %></button></a>
	                </p>
	                <%} 
                } %>
              </div>
            </div>
          </div>     
        </div>
        <div class="row mx-3 flex-column flex-md-row"> 
     	 <% if(dtu !=null && DtTurista.class.isInstance(dtu) && !movil){ %>
             <form id="formSub" action="consultaActividad" method="POST" accept-charset="utf-8">
                <div class="form-row">
                  <div>
                  	<input name="idAct" style="display:none" value="<%=id%>">
                  </div>
                  <div class="form-group col-md-6">
                    <% if (!esFavorito){ %>
			           	<input style='display:none' name="favorito" value= "true">
			           	<button type="submit" class="btn btn-outline-danger mt-3"  style="min-width: 8rem;">Agregar a Favoritos</button>
			        <% } else { %>
			           	<input style='display:none' name="favorito" value= "false">
			           	<button type="submit" class="btn btn-outline-danger mt-3"  style="min-width: 8rem;">Quitar de Favoritos</button>
			       <% } %>
                  </div>
           	   </div>
            </form>
        <% } %>
      </div>     
        
        
        <div class="card text-center ml-3 mt-3" style="max-width: 800px;">
        
          <div class="card-header">
            <nav class="nav nav-pills card-header-pills flex-column flex-md-row">
              <a class="nav-link active" id="linkD" href="#perfil" data-toggle="tab">Información</a>
              <a class="nav-link" id="linkD" href="#salidas" data-toggle="tab">Salidas para la actividad</a>
              <a class="nav-link" id="linkD" href="#paquetes" data-toggle="tab">Paquetes que contienen la actividad</a>
            </nav>
          </div>
          
          <div class="tab-content py-4">
            <div class="tab-pane align-left active" id="perfil">
              <h5 class="card-title">Nombre</h5>
              <label><%=actividad.getNombre() %></label>
              <h5 class="card-title">Descripción</h5>
              <label><%=actividad.getDescripcion() %></label>
              <h5 class="card-title">Duracion</h5>
              <label><%=actividad.getDuracion() %> días</label>
              <h5 class="card-title">Costo por turista</h5>
              <label><%=actividad.getCostoTurista() %></label>
              <h5 class="card-title">Ciudad</h5>
              <label><%=actividad.getCiudad() %></label>
              <h5 class="card-title">Fecha de alta</h5>
              <label><%=fechaAlta %></label>
              <h5 class="card-title">Estado</h5>
              <label>Confirmada</label>
              <%
              if(DtTurista.class.isInstance(dtu)){%>
            	<h5 class="card-title">Estado de favorita</h5>
              	<%if(esFavorito) {%>
                  <label>Es Favorita</label>
                <%}else{%>
                  <label>No es Favorita</label>
                <%}
              }
              if(actividad.getUrlVideo()!=null) {%>
            	  <h5 class="card-title">Video</h5>
                  <iframe src="<%=actividad.getUrlVideo()%>"></iframe>
			  <%}%>
              
            </div>

            <div class="tab-pane" id="salidas">
              <div class="col col-lg-9 py-4">
                <div class="container">
                  <div class="row g-3">
  					
  					<%
  					List<StringDTSalidaMap.Entry> sals = salidas.getEntry();
                  	Iterator<StringDTSalidaMap.Entry> it = sals.iterator();
          			StringDTSalidaMap.Entry key;
          			DtSalida sal = null;
          			while (it.hasNext()) {
          				key = it.next();
          				sal = key.getValue();
	                %>
                    <div class="col-12 col-md-6 col-lg-4">
                      <div class="card my-2" style="max-width: 460px">
                        <img src="imagenes?tipo=salida&id=<%=sal.getNombre()%>" class="card-img-left" style="max-height: 300px">
                        <div class="card-body">
                            <h5 class="card-title"><%=sal.getNombre() %></h5>
                            <a href="consultaSalida?id=<%=sal.getNombre() %>" class="btn btn-outline-secondary my-2 my-sm-0">Leer más</a>
                        </div>
                      </div>
                    </div> 
            		<%} %>
                  </div>
                </div>
              </div>
            </div>

            <div class="tab-pane" id="paquetes">
              <div class="col col-lg-9 py-4">
                <div class="container">
                  <div class="row g-3">
        			
        			<%
        			List<StringDTPaqueteMap.Entry> paqs = paquetes.getEntry();
                  	Iterator<StringDTPaqueteMap.Entry> it2 = paqs.iterator();
          			StringDTPaqueteMap.Entry key2;
          			DtPaquete paq = null;
          			while (it2.hasNext()) {
          				key2 = it2.next();
          				paq = key2.getValue();
	                %>
	                    <div class="col-12 col-md-6 col-lg-4">
	                      <div class="card my-2">
	                        <img src="imagenes?tipo=paquete&id=<%=paq.getNombre()%>" class="card-img-left" style="max-height: 300px">
	                        <div class="card-body">
	                            <h5 class="card-title"><%=paq.getNombre() %></h5>
	                            <p class="card-text"><%=paq.getDescripcion() %></p>
	                            <%if (!movil){ %><a href="/turismoUy/consultaPaquete?id=<%=paq.getNombre() %>" class="btn btn-outline-secondary my-2 my-sm-0">Leer más</a><% } %>
	                        </div>
	                      </div>
	                    </div> 
				   <% } %>
                  </div>
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

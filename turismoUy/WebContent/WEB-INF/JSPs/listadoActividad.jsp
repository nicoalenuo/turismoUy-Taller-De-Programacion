<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>

<html lang="en">
<head>

	<%@ page import="servidorusuarios.DtUsuario" %>
	<%@ page import="servidorusuarios.DtTurista" %>
	<%@ page import="servidoractividades.DtActividad" %>
	<%@ page import="servidoractividades.DtDepartamento" %>
	<%@ page import="servidoractividades.DtCategoria" %>
	<%@ page import="servidoractividades.StringDTActividadMap" %>
	<%@ page import="servidoractividades.SetOfString" %>
	<%@ page import="java.util.*" %>

	<%
		StringDTActividadMap actividades = (StringDTActividadMap) request.getAttribute("actividadesListar");
		SetOfString actividadesFav = (SetOfString) request.getAttribute("actividadesFavoritas");
		List<String> actividadesFavoritas =null;
		String tipo= (String) request.getAttribute("tipo");
		DtUsuario dtu= (DtUsuario) request.getSession().getAttribute("datosUsuarioSesion");
	%>

  <jsp:include page="/WEB-INF/Template/head.jsp"/>

</head>

<body>

  <jsp:include page="/WEB-INF/Template/header.jsp"/>

  <div style="height: 65px;"></div>
  <div class="container-fluid">
    <div class="row">
      
      <jsp:include page="/WEB-INF/Template/menuIzq.jsp"/>

		 <%if(tipo.equals("categoria")){ %>
		 	<%String categoria= (String) request.getAttribute("categoria"); %>
			 <div class="col py-4"> 
	          <div class="row border-bottom mb-3" style="max-width: 80rem">
	            <div class="col">
	              <h2>Actividades de <%=categoria %></h2>
	            </div>
	          </div>
	          <div class="row flex-column flex-md-row">
	            <div class="col py-4 px-4">
	              <div class="row g-3">
	
					<%
					if(actividades!=null && !actividades.getEntry().isEmpty()){
						List<StringDTActividadMap.Entry> acts = actividades.getEntry();
	                  	Iterator<StringDTActividadMap.Entry> it = acts.iterator();
	          			StringDTActividadMap.Entry key;
	          			DtActividad act = null;
	          			if(DtTurista.class.isInstance(dtu) && actividadesFav!=null){
	          				actividadesFavoritas= actividadesFav.getItem();
	          			}
	          			while (it.hasNext()) {
	          				key = it.next();
	          				act = key.getValue();
		        				
		                %>
		                <div class="col-12 col-md-6 col-lg-4 col-xl-3">
		                  <div class="card my-2">
		                    <img src="imagenes?tipo=actividad&id=<%=act.getNombre() %>" class="card-img-left" style="max-height: 300px">
		                    <div class="card-body">
		                      <h5 class="card-title"><%=act.getNombre() %></h5>
		                      <p class="card-text"><%=act.getDescripcion()%></p>
		                      <p class="card-text">Cantidad de turistas que le dieron favorito: <%=act.getCantTuristasFavoritos() %></p>
		                      <%
		                      if(DtTurista.class.isInstance(dtu) && actividadesFavoritas!=null){
	                      			if(actividadesFavoritas.contains(act.getNombre())){
	                      				%>
		                      			<p class="card-text">Favorito</p>
		                      			<%
	                      			}else{
	                      				%>
		                      			<p class="card-text">No favorito</p>
		                      			<%
	                      			}
	                      	  }else if(DtTurista.class.isInstance(dtu)){
	                      		%>
                      			<p class="card-text">No favorito</p>
                      			<%
	                      	  }
		                      
		                      %>
		                      <a href="consultaActividad?id=<%=act.getNombre() %>" class="btn btn-outline-secondary my-2 my-sm-0">Leer más</a>
		                    </div>
		                  </div>
		                </div>  
						<%} 
					}%>
	
	              </div>
	            </div>
	          </div>  
	        </div>     
	    <% }else if(tipo.equals("departamento")){ %>
	    	<%String departamento= (String) request.getAttribute("departamento"); %>
	    	<div class="col py-4"> 
	          <div class="row border-bottom mb-3" style="max-width: 80rem">
	            <div class="col">
	              <h2>Actividades de <%=departamento %></h2>
	            </div>
	          </div>
	          <div class="row flex-column flex-md-row">
	            <div class="col py-4 px-4">
	              <div class="row g-3">
	
					<%
					if(actividades!=null && !actividades.getEntry().isEmpty()){
						List<StringDTActividadMap.Entry> acts = actividades.getEntry();
	                  	Iterator<StringDTActividadMap.Entry> it = acts.iterator();
	          			StringDTActividadMap.Entry key;
	          			DtActividad act = null;
	          			if(DtTurista.class.isInstance(dtu) && actividadesFav!=null){
	          				actividadesFavoritas= actividadesFav.getItem();
	          			}
	          			while (it.hasNext()) {
	          				key = it.next();
	          				act = key.getValue();
		        				
		                %>
		                <div class="col-12 col-md-6 col-lg-4 col-xl-3">
		                  <div class="card my-2">
		                    <img src="imagenes?tipo=actividad&id=<%=act.getNombre() %>" class="card-img-left" style="max-height: 300px">
		                    <div class="card-body">
		                      <h5 class="card-title"><%=act.getNombre() %></h5>
		                      <p class="card-text"><%=act.getDescripcion()%></p>
		                      <p class="card-text">Cantidad de turistas que le dieron favorito: <%=act.getCantTuristasFavoritos() %></p>
		                      <%
		                      if(DtTurista.class.isInstance(dtu) && actividadesFavoritas!=null){
	                      			if(actividadesFavoritas.contains(act.getNombre())){
	                      				%>
		                      			<p class="card-text">Favorito</p>
		                      			<%
	                      			}else{
	                      				%>
		                      			<p class="card-text">No favorito</p>
		                      			<%
	                      			}
	                      	  }else if(DtTurista.class.isInstance(dtu)){
	                      		%>
                      			<p class="card-text">No favorito</p>
                      			<%
	                      	  }
		                      
		                      %>
		                      <a href="consultaActividad?id=<%=act.getNombre() %>" class="btn btn-outline-secondary my-2 my-sm-0">Leer más</a>
		                    </div>
		                  </div>
		                </div>  
						<%} 
					}%>
				  </div>
				 </div>
				<%}%>
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

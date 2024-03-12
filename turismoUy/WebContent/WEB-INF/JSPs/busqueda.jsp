<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>

<html lang="en">
<head>

	<jsp:include page="/WEB-INF/Template/head.jsp"/>
	
	<%@ page import="servidoractividades.StringDTActividadMap" %>
	<%@ page import="servidoractividades.DtActividad" %>
	<%@ page import="servidoractividades.DtCategoria" %>
	<%@ page import="servidoractividades.DtDepartamento" %>
	<%@ page import="servidorpaquetes.StringDTPaqueteMap" %>
	<%@ page import="servidoractividades.StringDTDepartamentoMap" %>
	<%@ page import="servidoractividades.StringDTCategoriaMap" %>
	<%@ page import="servidorpaquetes.DtPaquete" %>
	<%@ page import="java.util.*" %>
	
	<%

	StringDTDepartamentoMap departamentos = (StringDTDepartamentoMap) request.getAttribute("departamentos");
	StringDTCategoriaMap categorias = (StringDTCategoriaMap) request.getAttribute("categorias");
	List<StringDTDepartamentoMap.Entry> deptos = departamentos.getEntry();
	List<StringDTCategoriaMap.Entry> cats = categorias.getEntry();
	
	String departamento = (String) request.getParameter("departamento");
	String categoria = (String) request.getParameter("categoria");
	
	StringDTActividadMap actividades = (StringDTActividadMap) request.getAttribute("actividades");
	List<StringDTActividadMap.Entry> acts = actividades.getEntry();
	
	StringDTPaqueteMap paquetes = (StringDTPaqueteMap) request.getAttribute("paquetes");
	List<StringDTPaqueteMap.Entry> paqs = paquetes.getEntry();
	
	%>

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
              <h2>Resultados de <%=request.getParameter("q") %></h2>
            </div>
          </div>
          <div class="row flex-column flex-md-row">
            <div class="col py-4 px-4">
            <form  method="GET" action="busqueda" accept-charset="utf-8">
            	<input type="hidden" name="q" value="<%=request.getParameter("q")%>">
                <select name="departamento" onchange='{ this.form.submit(); }'>
                     
                     
                     <% if (departamento == null || departamento.equals("Todos los departamentos")) { %>
                              <option selected>Todos los departamentos</option>
                            <%} else { %>
                            	<option>Todos los departamentos</option>
                                <option selected> <%=departamento%> </option>
                            <%}%>
                            <%Iterator<StringDTDepartamentoMap.Entry> itDep = deptos.listIterator();
                                StringDTDepartamentoMap.Entry keyDep;
                                DtDepartamento d = null;
                                while (itDep.hasNext()) {
                                    keyDep = itDep.next();
                                    d = keyDep.getValue();
                                    
                                    %> 
                                    <option value="<%=d.getNombre()%>"><%=d.getNombre()%></option>
                                <% } %>
                     
              </select>
              <select name="categoria" onchange='{ this.form.submit(); }'>
                     <% if (categoria == null || categoria.equals("Todas las categorias")) { %>
                              <option selected>Todas las categorias</option>
                            <%} else { %>
                            <option>Todas las categorias</option>
                            <option selected> <%=categoria%> </option>
                            <%}%>
                            <%Iterator<StringDTCategoriaMap.Entry> itCat = cats.listIterator();
                                StringDTCategoriaMap.Entry keyCat;
                                DtCategoria c = null;
                                while (itCat.hasNext()) {
                                    keyCat = itCat.next();
                                    c = keyCat.getValue();
                                    
                                    %> 
                                    <option value="<%=c.getNombre()%>"><%=c.getNombre()%></option>
                                <% } %>
                     
              </select>
              </form>
              <div class="row g-3">
              
              

               <%
               	Iterator<StringDTActividadMap.Entry> it = acts.iterator();
       			StringDTActividadMap.Entry key;
       			DtActividad act = null;
       			while (it.hasNext()) {
       				key = it.next();
       				act = key.getValue();
	          %>
              <div class="col-12 col-md-6 col-lg-4 col-xl-3">
                <div class="card my-2">
                  <img src="imagenes?tipo=actividad&id=<%=act.getNombre()%>" class="card-img-left" style="max-height: 300px">
                  <div class="card-body">
                    <h5 class="card-title"><%= act.getNombre() %></h5>
                    <p class="card-text"><%= act.getDescripcion() %></p>
                    <a href="consultaActividad?id=<%=act.getNombre()%>" class="btn btn-outline-secondary my-2 my-sm-0">Leer más</a>
                  </div>
                </div>
              </div>  
      		  <% } %>
      		  
      		  <%
               	Iterator<StringDTPaqueteMap.Entry> it2 = paqs.iterator();
       			StringDTPaqueteMap.Entry key2;
       			DtPaquete paq = null;
       			while (it2.hasNext()) {
       				key2 = it2.next();
       				paq = key2.getValue();
	          %>
              <div class="col-12 col-md-6 col-lg-4 col-xl-3">
                <div class="card my-2">
                  <img src="imagenes?tipo=paquete&id=<%=paq.getNombre()%>" class="card-img-left" style="max-height: 300px">
                  <div class="card-body">
                    <h5 class="card-title"><%= paq.getNombre() %></h5>
                    <p class="card-text"><%= paq.getDescripcion() %></p>
                    <a href="consultaPaquete?id=<%=paq.getNombre()%>" class="btn btn-outline-secondary my-2 my-sm-0">Leer más</a>
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
  
  <footer id="sticky-footer" class="py-4 bg-light text-black-50 border">
      <div class="container text-center">
        <small>Copyright &copy; turismo.Uy</small>
      </div>
  </footer>

  <script src="media/js/menuIzq.js"></script>  

</body>
</html>
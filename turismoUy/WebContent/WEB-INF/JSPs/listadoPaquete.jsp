<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>

<html lang="en">
<head>

  <jsp:include page="/WEB-INF/Template/head.jsp"/>

</head>

	<%@ page import="servidorpaquetes.DtPaquete" %>
	<%@ page import="servidorpaquetes.StringDTPaqueteMap" %>
	<%@ page import="java.util.Iterator" %>
	
	<% 
	StringDTPaqueteMap paquetes = (StringDTPaqueteMap) request.getAttribute("paquetes");
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
            <h2>Paquetes</h2>
          </div>
        </div>
        <div class="row flex-column flex-md-row">
          <div class="col py-4 px-4">
            <div class="row g-4">


		      <%
               	Iterator<StringDTPaqueteMap.Entry> it = paquetes.getEntry().iterator();
       			StringDTPaqueteMap.Entry key;
       			DtPaquete paq = null;
       			while (it.hasNext()) {
       				key = it.next();
       				paq = key.getValue();
	          %>
              <div class="col-12 col-md-6 col-lg-4 col-xl-3">
                <div class="card my-2">
                  <img src="imagenes?tipo=paquete&id=<%=paq.getNombre() %>" class="card-img-left" style="max-height: 300px">
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

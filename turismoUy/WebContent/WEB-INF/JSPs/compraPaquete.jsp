<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>

  <jsp:include page="/WEB-INF/Template/head.jsp"/>
  	<%@ page import="servidoractividades.DtDepartamento" %>
	<%@ page import="servidoractividades.DtCategoria" %>
	<%@ page import="servidoractividades.StringDTDepartamentoMap" %>
	<%@ page import="servidoractividades.StringDTCategoriaMap" %>
  	<%@ page import="servidorpaquetes.StringDTPaqueteMap" %>
  	<%@ page import="servidorpaquetes.DtPaquete" %>
  	<%@ page import="java.util.*" %>
  	
	<%
	StringDTPaqueteMap paquetes= (StringDTPaqueteMap) request.getAttribute("paquetes");
	String error = (String) request.getParameter("error");
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
              <h2>Comprar paquete</h2>
          </div>
        </div>
        <div class="col-lg-5 col-sm-12 py-4 mx-auto">
    
            <form id="formSub" action="compraPaquete" method="POST" enctype="multipart/form-data" accept-charset="utf-8">
                  <div class="form-row col-md-5">
		              <select name="paquetes" class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">
		               <option value="Paquetes">Paquetes</option>
		               <%
		               if(!paquetes.getEntry().isEmpty()){
		            	    List<StringDTPaqueteMap.Entry> paqs = paquetes.getEntry();
		                  	Iterator<StringDTPaqueteMap.Entry> it = paqs.iterator();
		          			StringDTPaqueteMap.Entry key;
		          			DtPaquete paq=null;
		          			while (it.hasNext()) {
		          				key = it.next();
		          				paq= key.getValue();
			     			%> <option value="<%=paq.getNombre()%>"><%=paq.getNombre()%></option>
			                <% } 
			           } %>
			               
					  </select>
				 </div>
				 <% if (error!=null && error.equals("paquete")){ %>
	          		<small class="form-text text-black-50 ml-2" style="font-size: 12px">*Ingrese un paquete</small>  
	            <% } %>
	            
	            <div class="form-group col-md-6">
	              <label>Cantidad de turistas</label>
	              <input name="cantTur" class="form-control" required>
	            </div>
	            <% if (error!=null && error.equals("cantidad")){ %>
                  	<small class="form-text text-black-50 ml-2" style="font-size: 12px">*Ingrese una cantidad de turistas valida</small>  
                <% } %>
	            
                <button type="submit" class="btn btn-danger mt-3">Comprar</button>
                	<% if (error!=null && error.equals("compra")){ %>
                  	<small class="form-text text-black-50 ml-2" style="font-size: 12px">*Usted ya ha comprado ese paquete</small>  
                    <% } %>
              </form>
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

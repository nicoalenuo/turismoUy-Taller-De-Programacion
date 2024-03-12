<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>

  <jsp:include page="/WEB-INF/Template/head.jsp"/>
  
  <link rel="stylesheet" href="media/css/imgChooser.css">
	<%
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
              <h2>Registrar actividad</h2>
          </div>
        </div>
		
		<%@ page import="servidoractividades.DtDepartamento" %>
		<%@ page import="servidoractividades.DtCategoria" %>
		<%@ page import="servidoractividades.StringDTDepartamentoMap" %>
		<%@ page import="servidoractividades.StringDTCategoriaMap" %>
		<%@ page import="java.util.*" %>

		<% StringDTDepartamentoMap deps = (StringDTDepartamentoMap) request.getAttribute("departamentoListar"); %>
		<% StringDTCategoriaMap cats = (StringDTCategoriaMap) request.getAttribute("categoriaListar"); %>
        
         <div class="col-lg-5 col-sm-12 py-4 mx-auto">
          <form id="formSub" action="altaActividad" method="POST" enctype="multipart/form-data" accept-charset="utf-8">
            <div class="form-row">
             <div class="form-row col-md-5">
              <select name="departamentos" class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">
               <option value="Departamentos">Departamentos</option>
               <%
               if(deps!=null){
            	    List<StringDTDepartamentoMap.Entry> departamentos = deps.getEntry();
                 	Iterator<StringDTDepartamentoMap.Entry> it = departamentos.iterator();
         			StringDTDepartamentoMap.Entry key;
         			DtDepartamento dep = null;
	     			while (it.hasNext()) {
	     				key = it.next();
	     				dep= key.getValue();
	     			%> <option value="<%=dep.getNombre()%>"><%=dep.getNombre()%></option>
	                <% } 
	           } %>
	               
			  </select>
			 </div>
			 <div style="width: 120px;"></div>
             <div class="form-row col-md-5">
	           <table class="table table-sm">
                <thead>
                  <tr>
                    <th>Categorías</th>
                  </tr>
                </thead>
                <tbody>
	            	<%
	            	if(cats!=null){
	            		List<StringDTCategoriaMap.Entry> categorias = cats.getEntry();
	                 	Iterator<StringDTCategoriaMap.Entry> it2 = categorias.iterator();
	         			StringDTCategoriaMap.Entry key2;
	         			DtCategoria cat = null;
		     			while (it2.hasNext()) {
		     				key2 = it2.next();
		     				cat= key2.getValue();
		     			%> 
				     	<tr>
	                    	<td><input class="mr-2" name="categoria" type="checkbox" value="<%=cat.getNombre() %>" style="color:rgb(220, 53, 69)"/><a><%=cat.getNombre()%></a></td>
	                 	</tr>
		               	<% } %>  
                  <% } %>
                </tbody>
               </table>
			 </div>
            </div>
            
    		<% if (error!=null && error.equals("departamento")){ %>
          		<small class="form-text text-black-50 ml-2" style="font-size: 12px">*Ingrese un departamento</small>  
            <% } %>
      		<% if (error!=null && error.equals("categoria")){ %>
         		<small class="form-text text-black-50 ml-2" style="font-size: 12px">*Ingrese almenos una categoría</small>
            <% } %>
        	       	
            <div class="form-group">
	            <label>Nombre</label>
	            <input name="nombre" class="form-control" placeholder="Nombre" required>
            </div>
              
            <div class="form-group">
	            <label>Descripción</label>
	            <textarea name="descripcion" class="form-control" rows="3" required></textarea>
            </div>
		        
            <div class="form-row">
	            <div class="form-group col-md-6">
	              <label>Duración</label>
	              <input name="duracion" class="form-control" placeholder="Días" required>
	            </div>
	            <div class="form-group col-md-6">
	              <label>Costo</label>
	              <input name="costo" class="form-control" placeholder="Costo por persona" required>
	            </div>
            </div>
	        
 			<% if (error!=null && error.equals("duracion")){ %>
          		<small class="form-text text-black-50 ml-2" style="font-size: 12px">*Ingrese una duración válida</small>
            <% } %>
       		<% if (error!=null && error.equals("costo")){ %>
          		<small class="form-text text-black-50 ml-2" style="font-size: 12px">*Ingrese un costo válido</small>  
            <% } %>
            <% if (error!=null && error.equals("numero")){ %>
          		<small class="form-text text-black-50 ml-2" style="font-size: 12px">*Ingrese numeros en la duracion y costo</small>
            <% } %>
     
            <div class="form-group">
              <label>Ciudad</label>
              <input name="ciudad" class="form-control" placeholder="Ciudad" required>
            </div>  
                            
            <div class="form-row">
              <figure class="image-container" id="imgContainerImgChooser">
                <input name="imagen" type="file" id="upload-button" accept="image/*">
                <label class="border" for="upload-button" id="imgChooserLabel">
                  <i class="fa fa-upload" style="color:rgb(220, 53, 69)"></i>
                  Elige una foto
                </label>
                <img id="chosen-image">
              </figure>        
            </div>
            
            <div class="form-group">
              <label>Video</label>
              <input name="urlVideo" class="form-control" placeholder="url del video">
            </div>
	
            <button type="submit" class="btn btn-danger mt-3">Registrar Actividad</button>
            	<% if (error!=null && error.equals("actividad")){ %>
              		<small class="form-text text-black-50 ml-2" style="font-size: 12px">*Ya existe una actividad con el nombre ingresado</small>  
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
  <script src="media/js/imgChooser.js"></script>
  
</body>
</html>
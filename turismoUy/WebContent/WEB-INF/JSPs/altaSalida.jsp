<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>

  <jsp:include page="/WEB-INF/Template/head.jsp"/>
  
  <link rel="stylesheet" href="media/css/imgChooser.css"> 
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
              <h2>Alta de salida</h2>
          </div>
        </div>

        <%@ page import="servidorusuarios.DtUsuario" %>
        <%@ page import="servidorusuarios.DtTurista" %>
        <%@ page import="servidorusuarios.DtProveedor" %>
        <%@ page import="servidoractividades.DtDepartamento" %>
		<%@ page import="servidoractividades.DtCategoria" %>
		<%@ page import="servidoractividades.StringDTDepartamentoMap" %>
		<%@ page import="servidoractividades.StringDTCategoriaMap" %>
        <%@ page import="servidorsalidas.DtActividad" %>
        <%@ page import="servidorsalidas.StringDTActividadMap" %>
        <%@ page import="java.util.*" %>
        <% String dptoSeleccionado = (String) request.getAttribute("departamentoSeleccionado"); 
        	if(dptoSeleccionado==null){
        		dptoSeleccionado="Departamentos";
        	} %>
        <% StringDTDepartamentoMap deps = (StringDTDepartamentoMap) request.getAttribute("departamentoListar"); %> 
        <% StringDTActividadMap acts = (StringDTActividadMap) request.getAttribute("actividadesListar"); %> 
        <% String error = (String) request.getParameter("error"); %>
        <div class="col-lg-5 col-sm-12 py-4 mx-auto">
            
            <form method="GET" action="altaSalida" accept-charset="utf-8">
                <select name="departamento" onchange='{ this.form.submit(); }'>
                        <% if (deps!=null){ %>
                        	<% if (dptoSeleccionado != "Departamentos") {%>
                            	<option value="<%=dptoSeleccionado%>"><%=dptoSeleccionado%></option>
	                        <% }else{ %>
	                        	<option value="Departamentos">Departamentos</option>
	                        <% } %>
	                       <%List<StringDTDepartamentoMap.Entry> departamentos = deps.getEntry();
	                    	Iterator<StringDTDepartamentoMap.Entry> it = departamentos.iterator();
	            			StringDTDepartamentoMap.Entry key;
	            			DtDepartamento dep = null;
	    	     			while (it.hasNext()) {
	    	     				key = it.next();
	    	     				dep= key.getValue();%>
	                           	<option value="<%=dep.getNombre()%>"><%=dep.getNombre()%></option>
	    	     			<%}
	    	     		}else{ %>
                        	<option value="Departamentos">Departamentos</option>
                        <% } %>

                </select>
            </form>
           <form id="formSub" action="altaSalida" method="POST" enctype="multipart/form-data" accept-charset="utf-8">
              <div class="form-row">
                <div class="form-group col-md-6">
                  <select name="actividad" class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">
                    <% if (acts != null && acts.getEntry()!=null && !acts.getEntry().isEmpty()) {%>
	                    	<%
	                    	List<StringDTActividadMap.Entry> actividades = acts.getEntry();
		                 	Iterator<StringDTActividadMap.Entry> it2 = actividades.iterator();
		                 	StringDTActividadMap.Entry key2;
		         			DtActividad act = null;
			     			while (it2.hasNext()) {
			     				key2 = it2.next();
			     				act= key2.getValue();%>
	                            <option value="<%=act.getNombre()%>"><%=act.getNombre()%></option>
                        	<% } %>
                    <% }else{ %>
                    	<option value="Actividades">Actividades</option>
                    <%} %>    
                  </select>
                </div>
              </div>
              <% if (error!=null && error.equals("actividad")){ %>
          		<small class="form-text text-black-50 ml-2" style="font-size: 12px">*Ingrese una actividad</small>  
            <% } %>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>Nombre Salida</label>
                        <input name="nombre" class="form-control" placeholder="Nombre salida" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputCantMax">Cantidad maxima de turistas</label>
                        <input name="cantMax" type="number" min="0" value="0" class="form-control" placeholder="1" required>
                    </div>
                </div>
                <% if (error!=null && error.equals("nombre")){ %>
                  		<small class="form-text text-black-50 ml-2" style="font-size: 12px">*Ya existe una salida con ese nombre</small>  
                 <% } %>
                 <% if (error!=null && error.equals("cantidad")){ %>
                  		<small class="form-text text-black-50 ml-2" style="font-size: 12px">*La cantidad máxima de turistas debe ser mayor a 0</small>  
                 <% } %>
                <div class="form-row mb-3">
                    <label>Fecha de Salida</label>
                    <div class="input-group date dateChooser">
                      <span class="input-group-addon mr-2" style="margin:auto; color:rgb(220, 53, 69)"><i class="fa fa-calendar-minus-o" type="date" style="cursor: pointer;"></i></span><input name="fechaS" type="text" class="form-control" required>
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label>Lugar de Salida</label>
                    <input name="lugar" class="form-control" placeholder="Nombre salida" required>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputHH">Hora</label>
                        <input name="hora" type="time" class="form-control" required>
                      </div>
                </div>
            
                <div class="form-row">
                  <figure class="image-container" id="imgContainerImgChooser">
                    <input name="imagen" type="file" id="upload-button" accept="image/*">
                    <label class="border" for="upload-button" id="imgChooserLabel">
                      <i class="fa fa-upload" style="color:rgb(220, 53, 69)"></i>
                      Elige una imagen para la salida
                    </label>
                    <img id="chosen-image">
                  </figure>        
                </div>

                <button type="submit" class="btn btn-danger mt-3">Confirmar</button>
                
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
  <script src="media/js/bootstrap-datepicker.min.js"></script>
  <script src="media/js/bootstrap-datepicker.es.min.js"></script>
  <script src="media/js/dateChooser.js"></script>
  <link rel="stylesheet" href="media/css/bootstrap-datepicker.css">
  
  
</body>
</html>